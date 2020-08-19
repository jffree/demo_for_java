package com.jffree.java_demo.network_model.reactor_with_multiHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final public class Handler implements Runnable {
    private final SocketChannel      socket;
    private final SelectionKey       sk;
    private final Selector           selector;
    private final ByteBuffer         input    = ByteBuffer.allocate(1000);
    private final ByteBuffer         output   = ByteBuffer.allocate(1000);               ;
    private final static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS,
                                            new LinkedBlockingDeque<Runnable>(40));
    private Sender             sender;

    public Handler(Selector sel, SocketChannel c) throws IOException {
        selector = sel;
        socket = c;
        socket.configureBlocking(false);
        sk = socket.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }


    //sender 依然跟处理接受请求在同一个线程，根据 reactor 的模型，这里应该只有发送数据，而没有编解码处理
    public class Sender implements Runnable {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public void run() {
            output.put((df.format(new Date()) + " server send data.\n").getBytes());
            if (outputIsComplete()) {
                output.flip();
                try {
                    socket.write(output);
                    sk.attach(Handler.this);
                    sk.interestOps(SelectionKey.OP_READ);
                } catch (IOException e) {
                    e.printStackTrace();
                    sk.cancel();
                }
                output.clear();
            }
        }
    }

    public class Processor implements Runnable {
        @Override
        public void run() {
            process();
        }
    }

    @Override
    public void run() {
        try {
            read();
        } catch (IOException e) {
            e.printStackTrace();
            sk.cancel();
        }
    }

    public boolean inputIsComplete() throws IOException {
        int readBytes = socket.read(input);
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

    public boolean outputIsComplete() {
        return true;
    }

    synchronized public void process() {
        input.flip();
        byte[] bytes = new byte[input.limit()];
        input.get(bytes, 0, input.limit());
        input.clear();
        String s = new String(bytes);
        System.out.println(String.format("Server receive data form port %d : %s ", socket.socket().getPort(), s));
        if (sender == null) {
            sender = new Sender();
        }
        sk.interestOps(SelectionKey.OP_WRITE);
        sk.attach(sender);
        selector.wakeup();
    }

    synchronized public void read() throws IOException {
        if (inputIsComplete()) {
            executor.execute(new Processor());
        }
    }
}
