package com.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author: xwh90
 * @date: 2023/7/7 16:07
 * @description:
 */
public class LenthFieldBaseFrameDecoderTest {
    @Test
    public void testDelimiterBaseFrameDecoder() {
        String content = "疯狂创客圈:高性能学习社群!";
        LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder = new LengthFieldBasedFrameDecoder(1024,0,4,0,4);
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(lengthFieldBasedFrameDecoder);
                ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
                ch.pipeline().addLast(new StringProcessHandler());

            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);

        for (int i = 1; i < 100; i++) {
            ByteBuf byteBuf = Unpooled.buffer();
            String tmp = i + "次发送->"+content;
            final byte[] bytes = tmp.getBytes(StandardCharsets.UTF_8);
            byteBuf.writeInt(bytes.length);
            byteBuf.writeBytes(bytes);
            channel.writeInbound(byteBuf);
        }


    }
}
