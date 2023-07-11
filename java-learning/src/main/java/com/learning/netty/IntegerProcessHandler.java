package com.learning.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: xwh90
 * @date: 2023/7/7 16:05
 * @description:
 */
@Slf4j
public class IntegerProcessHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final Integer integer = (Integer) msg;
        log.info("处理一个整数:" + integer);
        super.channelRead(ctx, msg);
    }
}
