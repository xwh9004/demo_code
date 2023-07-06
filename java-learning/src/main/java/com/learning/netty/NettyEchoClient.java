package com.learning.netty;

import com.learning.util.DataTimeUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * @author: xwh90
 * @date: 2023/6/6 16:11
 * @description: netty demo
 */
@Slf4j
public class NettyEchoClient {

    private final int serverPort;

    private Bootstrap bootstrap = new Bootstrap();

    public NettyEchoClient(int port) {
        this.serverPort = port;
    }

    public void runServer() {
        //创建反应器轮询组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);

        EventLoopGroup workLoopGroup = new NioEventLoopGroup();

        try {
            //1 设置反应器轮询组
            bootstrap.group(workLoopGroup);
            //设置nio类型的通道
            bootstrap.channel(NioSocketChannel.class);
            //设置端口号
            bootstrap.remoteAddress("127.0.0.1", serverPort);
            //设置通道参数
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            //装配通道流水线
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //流水线的职责：负责管理通道中的处理器
                    socketChannel.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                }
            });

            final ChannelFuture channelFuture = bootstrap.connect();

            channelFuture.addListener(listener -> {
                if (listener.isSuccess()) {
                    log.info("客户端连接成功");
                } else {
                    log.info("客户端连接失败");
                }
            });
            //阻塞直到连接成功
            channelFuture.sync();

            final Channel channel = channelFuture.channel();

            final Scanner scanner = new Scanner(System.in);
            log.info("请输入发送的内容:");
            while (scanner.hasNext()) {
                final String next = scanner.next();
                byte[] bytes = (DataTimeUtil.now() + " >>" + next).getBytes("UTF-8");
                final ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
                log.info("请输入发送的内容:");
            }

        } catch (InterruptedException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            //8 优雅关闭EventLoopGroup
            workLoopGroup.shutdownGracefully();

            bossLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyEchoClient(18899).runServer();
    }
}
