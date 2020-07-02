package com.jffree.java_demo.network_model.aio_model;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompleteHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {

    @Override
    public void completed(AsynchronousSocketChannel result, AioServer attachment) {
        attachment.getServerSocketChannel().accept(attachment, this); //  try accept next connection
        ByteBuffer buffer = ByteBuffer.allocate(1000);
        result.read(buffer, null, new ReadCompleteHandler(result, buffer));
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.printStackTrace();
        attachment.cancel();
    }
}
