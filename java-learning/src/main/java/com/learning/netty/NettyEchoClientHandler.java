package com.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: xwh90
 * @date: 2023/6/6 16:22
 * @description:
 */
@Slf4j
public class NettyEchoClientHandler extends ChannelInboundHandlerAdapter {

    public static final NettyEchoClientHandler INSTANCE = new NettyEchoClientHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

//        log.info("msg type: {}",byteBuf.hasArray()?"堆内存":"直接内存");

        final int len = byteBuf.readableBytes();
        //读出数据
        byte[] arr = new byte[len];
        byteBuf.getBytes(0,arr);

        log.info("client received: " + new String(arr,"UTF-8"));
        byteBuf.release();

    }
}
