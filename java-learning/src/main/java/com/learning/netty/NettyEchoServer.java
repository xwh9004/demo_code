package com.learning.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author: xwh90
 * @date: 2023/6/6 16:11
 * @description: netty demo
 */
@Slf4j
public class NettyEchoServer {

    private final int serverPort;

    private ServerBootstrap bootstrap = new ServerBootstrap();

    public NettyEchoServer(int port) {
        this.serverPort = port;
    }

    public void runServer() {
        //创建反应器轮询组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);

        EventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try {
            //1 设置反应器轮询组
            bootstrap.group(workLoopGroup, workLoopGroup);
            //设置nio类型的通道
            bootstrap.channel(NioServerSocketChannel.class);
            //设置端口号
            bootstrap.localAddress(serverPort);
            //设置通道参数
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);

            LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder = new LengthFieldBasedFrameDecoder(1024,0,4,0,4);

            //装配子通道流水线
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //流水线的职责：负责管理通道中的处理器
                    socketChannel.pipeline().addLast(lengthFieldBasedFrameDecoder);
                    socketChannel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                    socketChannel.pipeline().addLast(new StringProcessHandler());
                }
            });

            final ChannelFuture channelFuture = bootstrap.bind().sync();
            log.info("服务器启动成功，监听端口:{}", channelFuture.channel().localAddress());
            //等待通道关闭的异步任务结束，服务监听通道会一直等待通道关闭的异步任务结束
            final ChannelFuture closeFuture = channelFuture.channel().closeFuture();

            closeFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //8 优雅关闭EventLoopGroup
            workLoopGroup.shutdownGracefully();

            bossLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyEchoServer(18899).runServer();
    }
}
