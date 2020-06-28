package cn.wthinker.java_demo.network_model.aio_model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AioServer extends Thread {
    private String                          host;
    private int                             port;
    private AsynchronousServerSocketChannel serverSocketChannel;
    private CountDownLatch                  countDownLatch = new CountDownLatch(1);

    public AioServer(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(host, port));
        System.out.println("Server run on port: " + this.port);
    }

    public void cancel() {
        countDownLatch.countDown();
    }

    public AsynchronousServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    @Override
    public void run() {
        serverSocketChannel.accept(this, new AcceptCompleteHandler());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            serverSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new AioServer("0.0.0.0", 6211).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
