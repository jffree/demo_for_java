package com.jffree.java_demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
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

    }

}
