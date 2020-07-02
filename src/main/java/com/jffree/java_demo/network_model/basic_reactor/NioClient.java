package com.jffree.java_demo.network_model.basic_reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/*
 * Ref: 《Netty 权威指南》 第二版
 */

public class NioClient extends Thread {
    private static AtomicInteger id     = new AtomicInteger();
    private Selector             selector;
    private SocketChannel        socketChannel;
    private SelectionKey         sk;
    final private ByteBuffer     input  = ByteBuffer.allocate(1000);
    final private ByteBuffer     output = ByteBuffer.allocate(1000);
    private Reader               reader;
    private Sender               sender;
    private int                  port;
    private String               host;
    private int                  clientId;

    public NioClient(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        clientId = id.getAndIncrement();
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        if (socketChannel.connect(new InetSocketAddress(this.host, this.port))) {
            System.out.println(String.format("Start client %d on port: %d", clientId, socketChannel.socket()
                .getLocalPort()));
            sk = socketChannel.register(selector, 0);
            if (sender == null) {
                sender = new Sender();
            }
            sk.attach(sender);
            sk.interestOps(SelectionKey.OP_WRITE);
        } else {
            sk = socketChannel.register(selector, 0);
            sk.attach(new Connector());
            sk.interestOps(SelectionKey.OP_CONNECT);
        }
    }

    public class Connector implements Runnable {
        @Override
        public void run() {
            try {
                if (socketChannel.finishConnect()) {
                    System.out.println(String.format("Start client %d on port: %d", clientId, socketChannel.socket()
                        .getLocalPort()));
                    if (sender == null)
                        sender = new Sender();
                    sk.attach(sender);
                    sk.interestOps(SelectionKey.OP_WRITE);
                }
            } catch (IOException e) {
                e.printStackTrace();
                sk.cancel();
            }

        }
    }

    public class Reader implements Runnable {
        public boolean inputIsComplete() throws IOException {
            int readBytes = socketChannel.read(input);
            if (readBytes > 0)
                return true;
            else if (readBytes == 0) {
                return false;
            } else {
                System.out.println("Remote socket closed!");
                sk.cancel();
                return false;
            }
        }

        @Override
        public void run() {
            try {
                if (inputIsComplete()) {
                    input.flip();
                    byte[] bytes = new byte[input.limit()];
                    input.get(bytes, 0, input.limit());
                    input.clear();
                    String s = new String(bytes);
                    System.out.println(String.format("Client %d received: %s", clientId, s));
                    sk.attach(sender);
                }
            } catch (IOException e) {
                e.printStackTrace();
                sk.cancel();
            }
        }
    }

    public class Sender implements Runnable {
        public boolean outputIsComplete() {
            return true;
        }

        @Override
        public void run() {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            output.put((String.format("%s client %d send data.", df.format(new Date()), clientId)).getBytes());
            if (outputIsComplete()) {
                output.flip();
                try {
                    socketChannel.write(output);
                    if (reader == null) {
                        reader = new Reader();
                        sk.interestOps(SelectionKey.OP_READ);
                    }
                    sk.attach(reader);
                } catch (IOException e) {
                    e.printStackTrace();
                    sk.cancel();
                }
                output.clear();
            }
        }
    }

    void dispatch(SelectionKey sk) {
        Runnable r = (Runnable) (sk.attachment());
        if (r != null)
            r.run();
    }

    @Override
    public void run(){
        try{
            while (!Thread.interrupted()){
                selector.select(1000);
                Set<SelectionKey> selected = selector.selectedKeys();
                selected.forEach(sk -> dispatch((SelectionKey) sk));
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                selector.close();
            } catch (IOException e1) {
                e1.printStackTrace();
                sk.cancel();
            }
        }
    }

    public static void main(String[] args) {
        IntStream.range(1,8).forEach(i -> {
            try {
                new NioClient("localhost", 6211).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
