package com.jffree.java_demo.network_model.basic_reactor;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

final public class Handler implements Runnable {
    final private SocketChannel socket;
    final private SelectionKey  sk;
    final private Selector      selector;
    final private ByteBuffer    input      = ByteBuffer.allocate(1000);
    final private ByteBuffer    output     = ByteBuffer.allocate(1000);
    private boolean             bindSender = false;

    public Handler(Selector sel, SocketChannel c) throws IOException {
        selector = sel;
        socket = c;
        socket.configureBlocking(false);
        sk = socket.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

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
                } catch (IOException e) {
                    e.printStackTrace();
                    sk.cancel();
                }
                output.clear();
            }
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

    public void process() {
        input.flip();
        byte[] bytes = new byte[input.limit()];
        input.get(bytes, 0, input.limit());
        input.clear();
        String s = new String(bytes);
        System.out.println(String.format("Server receive data form port %d : %s ", socket.socket().getPort(), s));
        if (bindSender == false) {
            sk.interestOps(SelectionKey.OP_WRITE);
            sk.attach(new Sender());
        }

    }

    public void read() throws IOException {
        if (inputIsComplete()) {
            process();
        }
    }
}
