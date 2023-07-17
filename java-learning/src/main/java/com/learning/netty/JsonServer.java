package com.learning.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.learning.pojo.User;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author: xwh90
 * @date: 2023/7/17 15:58
 * @description:
 */
@Slf4j
public class JsonServer {
    private final int serverPort;

    public JsonServer(int port) {
        this.serverPort = port;
    }

    public void runServer() {
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);

        EventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            //1 设置反应器轮询组
            bootstrap.group(bossLoopGroup, workLoopGroup);
            //设置nio类型的通道
            bootstrap.channel(NioServerSocketChannel.class);
            //设置端口号
            bootstrap.localAddress(serverPort);
            //设置通道参数
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);

            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
                    ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                    ch.pipeline().addLast(new JsonMsgDecoder());
                }
            });
            final ChannelFuture channelFuture = bootstrap.bind().sync();
            log.info("服务器启动成功，监听端口:{}", channelFuture.channel().localAddress());
            //等待通道关闭的异步任务结束，服务监听通道会一直等待通道关闭的异步任务结束
            final ChannelFuture closeFuture = channelFuture.channel().closeFuture();

            closeFuture.sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //8 优雅关闭EventLoopGroup
            workLoopGroup.shutdownGracefully();

            bossLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new JsonServer(18899).runServer();
    }

    static class JsonMsgDecoder extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            String json = (String) msg;

            final User user = JSON.parseObject(json, User.class);

            log.info("收到一个json数据包 =>> {}", user);
            super.channelRead(ctx, msg);
        }
    }

}
