package com.learning.netty;

import io.netty.buffer.ByteBuf;
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
public class String2IntegerEncoderTest {

    @Test
    public void testInteger2StringDecoder() {
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline()
                        .addLast(new Integer2ByteEncoder()); //  integer-->  byte
                ch.pipeline()
                        .addLast(new String2IntegerEncoder()); // String --> integer


            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);

        for (int i = 0; i < 100; i++) {
            String s = "i am "+ i;
            channel.write(s);
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
