package com.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: xwh90
 * @date: 2023/7/7 16:07
 * @description:
 */
@Slf4j
public class Integer2StringEncoderTest {

    @Test
    public void testInteger2StringDecoder() {
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline()
                        .addLast(new Integer2ByteEncoder()); // byte --> integer


            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);

        for (int i = 0; i < 100; i++) {
            channel.write(i);
        }
        channel.flush();

        ByteBuf buf = channel.readOutbound();
        while (buf != null) {
            final int i = buf.readInt();
            log.info("读出一个整数："+i);
            buf = channel.readOutbound();
        }
    }
}
