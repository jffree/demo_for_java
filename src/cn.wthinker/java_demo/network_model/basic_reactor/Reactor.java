package java_demo.network_model.basic_reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Set;

public class Reactor extends Thread{
    final Selector selector;
    final ServerSocketChannel serverSocket;
    int port;

    class Acceptor implements Runnable{
        @Override
        public void run(){
            try{
                SocketChannel c = serverSocket.accept();
                if (c != null) {
                    System.out.println("Server receive new connect from port: " + c.socket().getPort());
                    new Handler(selector, c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public Reactor(int port) throws IOException {
        this.port = port;
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(this.port));
        System.out.println("Server run on port: " + this.port);
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    void dispatch(SelectionKey sk){
        Runnable r = (Runnable) (sk.attachment());
        if(r != null)
            r.run();
    }

    @Override
    public void run(){
        try{
            while (!Thread.interrupted()){
                selector.select();
                Set selected = selector.selectedKeys();
                selected.forEach(sk -> dispatch((SelectionKey) sk));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new Reactor(6211).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
