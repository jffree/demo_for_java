package cn.wthinker.java_demo.network_model.aio_model;

import java_demo.network_model.reactor_with_subReactor.NioClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AioClient extends Thread {
    private String                    host;
    private int                       port;
    private AsynchronousSocketChannel socketChannel;
    private CountDownLatch            countDownLatch = new CountDownLatch(1);
    private static AtomicInteger      id             = new AtomicInteger();
    private int                       clientId;

    public AioClient(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        clientId = id.getAndIncrement();
        socketChannel = AsynchronousSocketChannel.open();
    }

    public class ConnectHandler implements CompletionHandler<AsynchronousSocketChannel, AioClient> {
        ByteBuffer sendBuffer = ByteBuffer.allocate(1000);

        @Override
        public void completed(AsynchronousSocketChannel result, AioClient attachment) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sendBuffer.put((String.format("%s client %d send data.", df.format(new Date()), clientId)).getBytes());
            attachment.socketChannel.write(sendBuffer, sendBuffer, new SenderHandler());
        }

        @Override
        public void failed(Throwable exc, AioClient attachment) {
            exc.printStackTrace();
            attachment.cancel();
        }
    }

    public class SenderHandler implements CompletionHandler<Integer, ByteBuffer> {
        private ByteBuffer readBuffer;

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            if (result < attachment.limit()) {
                socketChannel.write(attachment, attachment, this);
                return;
            }
            attachment.clear();
            if (readBuffer == null) {
                readBuffer = ByteBuffer.allocate(1000);
                socketChannel.read(readBuffer, readBuffer, new ReaderHandler());
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            exc.printStackTrace();
            AioClient.this.cancel();
        }
    }

    public class ReaderHandler implements CompletionHandler<Integer, ByteBuffer> {

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            if (result < 0)
                AioClient.this.cancel();
            attachment.flip();
            byte[] bytes = new byte[attachment.limit()];
            attachment.get(bytes);
            attachment.clear();
            String s = new String(bytes);
            System.out.println(String.format("Client %d received: %s", clientId, s));
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            exc.printStackTrace();
            AioClient.this.cancel();
        }
    }

    public AsynchronousSocketChannel socketChannel() {
        return socketChannel;
    }

    public void cancel() {
        countDownLatch.countDown();
    }

    @Override
    public void run() {
        socketChannel.connect(new InetSocketAddress(host, port));
        System.out.println(String.format("Start client %d.", clientId));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
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
