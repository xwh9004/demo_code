package com.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author: xwh90
 * @date: 2023/7/7 16:07
 * @description:
 */
public class DelimiterBaseFrameDecoderTest {
    @Test
    public void testDelimiterBaseFrameDecoder() {
        String content = "疯狂创客圈:高性能学习社群!";
        String splitter2 = "\t";
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {

                ByteBuf delimiter = Unpooled.copiedBuffer(splitter2.getBytes(StandardCharsets.UTF_8));
                ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, true, delimiter));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new StringProcessHandler());

            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);

        for (int i = 0; i < 100; i++) {
            ByteBuf byteBuf = Unpooled.buffer();
            byteBuf.writeBytes(content.getBytes(StandardCharsets.UTF_8));
            channel.writeInbound(byteBuf);
            if (i != 0 && i % 10 == 0) {
                byteBuf = Unpooled.buffer();
                byteBuf.writeBytes(byteBuf.writeBytes(splitter2.getBytes(StandardCharsets.UTF_8)));
                channel.writeInbound(byteBuf);
            }
        }


    }
}
