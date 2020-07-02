package com.jffree.java_demo.network_model.aio_model;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadCompleteHandler implements CompletionHandler<Integer, Void> {
    private AsynchronousSocketChannel socketChannel;
    private ByteBuffer                readBuffer;
    private ByteBuffer                sendBuffer;

    public ReadCompleteHandler(AsynchronousSocketChannel socketChannel, ByteBuffer buffer) {
        this.socketChannel = socketChannel;
        this.readBuffer = buffer;
    }

    public void process() {
        readBuffer.flip();
        byte[] bytes = new byte[readBuffer.limit()];
        readBuffer.get(bytes);
        readBuffer.clear();
        String s = new String(bytes);
        System.out.println(String.format("Server receive data : %s ", s));
        socketChannel.read(readBuffer, null, this);
    }

    public void send() {
        if (sendBuffer == null)
            sendBuffer = ByteBuffer.allocate(1000);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sendBuffer.put((df.format(new Date()) + " server send data.\n").getBytes());
        sendBuffer.flip();
        socketChannel.write(sendBuffer, sendBuffer, new WriteCompleteHandler(socketChannel));
    }

    @Override
    public void completed(Integer result, Void attachment) {
        if (result == -1) {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        process();
        send();
    }

    @Override
    public void failed(Throwable exc, Void attachment) {
        exc.printStackTrace();
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
