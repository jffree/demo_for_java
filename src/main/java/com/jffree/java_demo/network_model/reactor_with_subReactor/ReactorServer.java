package com.jffree.java_demo.network_model.reactor_with_subReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/*
 * Ref: Scalable IO in Java
 */

public class ReactorServer extends Reactor {
    private ServerSocketChannel serverSocket;
    private int                 port;
    private String              host;
    private Reactor[]           subReactors;

    public class Acceptor implements Runnable {
        int next = 0;

        @Override
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null) {
                    System.out.println("Server receive new connect from port: " + c.socket().getPort());
                    if (subReactors != null && subReactors.length > 0) {
                        new Handler(subReactors[next].selector, c);
                        System.out.println(String.format("Register client socket (port %d) on sub reactor %d", c
                            .socket().getPort(), next));
                        if (++next == subReactors.length)
                            next = 0;
                    } else
                        new Handler(selector, c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ReactorServer(String host, int port, int sub) throws IOException {
        super();
        this.port = port;
        this.host = host;
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(this.host, this.port));
        System.out.println("Server run on port: " + this.port);
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
        if (sub > 0) {
            subReactors = new Reactor[sub];
            for (int i = 0; i < sub; i++) {
                subReactors[i] = new Reactor();
                subReactors[i].start();
            }
        }
    }

    void dispatch(SelectionKey sk) {
        Runnable r = (Runnable) (sk.attachment());
        if (r != null)
            r.run();
    }

    public static void main(String[] args) {
        try {
            new ReactorServer("localhost", 6211, 0).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
