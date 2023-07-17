package com.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: xwh90
 * @date: 2023/7/13 16:59
 * @description:
 */
@Slf4j
public class String2IntegerEncoder extends MessageToMessageEncoder<String> {


    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {

        final char[] chars = msg.toCharArray();
        for (char c : chars) {
            if (c >= 48 && c <= 57) {
                out.add(new Integer(c));
            }
        }
    }
}
