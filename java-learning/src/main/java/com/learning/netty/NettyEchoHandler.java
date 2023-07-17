package com.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: xwh90
 * @date: 2023/6/6 16:22
 * @description:
 */
@Slf4j
public class NettyEchoHandler extends ChannelInboundHandlerAdapter {

    public static final NettyEchoHandler INSTANCE = new NettyEchoHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;

//        log.info("msg type: {}",in.hasArray()?"堆内存":"直接内存");

        final int len = in.readableBytes();
        //读出数据
        byte[] arr = new byte[len];
        in.getBytes(0,arr);

        log.info("server received: " + new String(arr,"UTF-8"));

        log.info("写回前 msg.refCnt:{}", in.refCnt());

        final ChannelFuture channelFuture = ctx.writeAndFlush(msg);
        log.info("写回后 msg.refCnt:{}", in.refCnt());
    }
}
