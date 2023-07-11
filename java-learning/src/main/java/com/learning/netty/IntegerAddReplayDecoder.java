package com.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: xwh90
 * @date: 2023/7/7 16:03
 * @description:
 */
@Slf4j
public class IntegerAddReplayDecoder extends ReplayingDecoder {

    enum PHASE {
        PHASE_1,
        PHASE_2
    }

    private int first;
    private int second;

    public IntegerAddReplayDecoder() {
        super(PHASE.PHASE_1);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        final Object state = state();
        if (state == PHASE.PHASE_1) {
            first = in.readInt();
            checkpoint(PHASE.PHASE_2);
        }
        if (state == PHASE.PHASE_2) {
            second = in.readInt();
            final int sum = first + second;
            out.add(sum);
            checkpoint(PHASE.PHASE_1);
        }
    }
}
