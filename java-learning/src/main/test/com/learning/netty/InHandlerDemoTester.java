package com.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.logging.Handler;

/**
 * @author: xwh90
 * @date: 2023/6/21 10:02
 * @description:
 */
public class InHandlerDemoTester {

    @Test
    public void inTest() {

        final InHandlerDemo inHandlerDemoA = new InHandlerDemo();
        inHandlerDemoA.setChannelName("A");

        final InHandlerDemo inHandlerDemoB = new InHandlerDemo();
        inHandlerDemoB.setChannelName("B");

        final InHandlerDemo inHandlerDemoC = new InHandlerDemo();
        inHandlerDemoC.setChannelName("C");

        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(inHandlerDemoA);
                ch.pipeline().addLast(inHandlerDemoB);
                ch.pipeline().addLast(inHandlerDemoC);
            }
        };

        ByteBuf byteBuf = Unpooled.buffer();

        EmbeddedChannel channel = new EmbeddedChannel(initializer);

        byteBuf.writeInt(1);
        //模拟入站，向嵌入式通道写一个入站数据包
        channel.writeInbound(byteBuf);
        channel.writeInbound(byteBuf);
        channel.flush();

        //模拟入站，再向嵌入式通道写一个入站数据包
        channel.writeInbound(byteBuf);

        channel.flush();
        //关闭通道
        channel.close();

    }

    @Test
    public void outTest() {

        final SimpleOutHandler simpleOutHandlerA = new SimpleOutHandler();
        simpleOutHandlerA.setChannelName("A");

        final SimpleOutHandler simpleOutHandlerB = new SimpleOutHandler();
        simpleOutHandlerB.setChannelName("B");

        final SimpleOutHandler simpleOutHandlerC = new SimpleOutHandler();
        simpleOutHandlerC.setChannelName("C");

        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(simpleOutHandlerA);
                ch.pipeline().addLast(simpleOutHandlerB);
                ch.pipeline().addLast(simpleOutHandlerC);
            }
        };

        ByteBuf byteBuf = Unpooled.buffer();

        EmbeddedChannel channel = new EmbeddedChannel(initializer);

        byteBuf.writeInt(1);
        //模拟入站，向嵌入式通道写一个入站数据包
        channel.writeOutbound(byteBuf);
        channel.flush();
        //关闭通道
        channel.close();

    }
}
