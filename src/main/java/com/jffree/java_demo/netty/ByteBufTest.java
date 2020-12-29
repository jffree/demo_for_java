package com.jffree.java_demo.netty;

import io.netty.buffer.*;
import org.junit.Assert;
import org.junit.Test;

public class ByteBufTest {

    @Test
    public void pooledByteBufAllocatorTest() {
        ByteBufAllocator alloc = PooledByteBufAllocator.DEFAULT;
        ByteBuf byteBuf = alloc.directBuffer(254);
        byteBuf.writeInt(126);
        Assert.assertEquals(byteBuf.readInt(), 126);
        byteBuf.release();
        //Test compositeByteBuf
        ByteBuf heapBuf = Unpooled.buffer(3);
        String way = "way";
        heapBuf.writeBytes(way.getBytes());
        ByteBuf directBuf = Unpooled.directBuffer(3);
        String lau = "lau";
        directBuf.writeBytes(lau.getBytes());
        CompositeByteBuf compositeBuffer = Unpooled.compositeBuffer(10);
        compositeBuffer.addComponents(true, heapBuf, directBuf);
        compositeBuffer.setByte(2, 'm');
        /*byte[] cache = new byte[3];
        compositeBuffer.readBytes(cache);*/
        System.out.println(compositeBuffer.readerIndex());
        System.out.println(compositeBuffer.readableBytes());

    }

}
