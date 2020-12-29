package com.jffree.java_demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.util.Map;

@ChannelHandler.Sharable
public class EchoServerHandler2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        AttributeKey<Map<String, Integer>> key = AttributeKey.valueOf("TestChild");
        Attribute<Map<String, Integer>> value = ctx.channel().attr(key);
        ByteBuf m = (ByteBuf) msg;
        System.out.println(String.format("%s, receive value: %d, pre send value: %d", ctx.channel().remoteAddress(), m.readInt(), value.get().get("xxxx")));
        m.writeInt(value.get().get("xxxx"));
        value.get().put("xxxx", value.get().get("xxxx") + 1);
        ctx.fireChannelRead(m);
    }

}
