package com.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

/**
 * @author: xwh90
 * @date: 2023/7/7 16:07
 * @description:
 */
public class IntegerAddDecoderTest {

    @Test
    public void testIntegerAddDecoder() {
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new IntegerAddReplayDecoder());
                ch.pipeline().addLast(new IntegerProcessHandler());

            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);

        for (int i = 0; i < 100; i++) {
            ByteBuf byteBuf = Unpooled.buffer();
            byteBuf.writeInt(i);
            channel.writeInbound(byteBuf);
        }
    }
}
