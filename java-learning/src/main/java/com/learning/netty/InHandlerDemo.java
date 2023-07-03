package com.learning.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: xwh90
 * @date: 2023/6/21 10:02
 * @description:
 */
@Slf4j
public class InHandlerDemo extends ChannelInboundHandlerAdapter {

    private String channelName = null;

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        log.info("{} 被调用: channelRegistered()",channelName);
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("{} 被调用: channelUnregistered()",channelName);

        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{} 被调用: channelActive()",channelName);

        super.channelActive(ctx);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{} 被调用: channelInactive()",channelName);
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("{} 被调用: channelRead()",channelName);
        log.info("{} ChannelHandlerContext ctx ={}",channelName,ctx.toString());
        super.channelRead(ctx, msg);
        ctx.pipeline().remove(this);
        log.info("{} 被调用: channelRead()调用完成，卸载自身成功",channelName);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("{} 被调用: channelReadComplete()",channelName);
        super.channelReadComplete(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("{} 被调用: handlerAdded()",channelName);
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("{} 被调用: handlerRemoved()",channelName);
        super.handlerRemoved(ctx);
    }
}
