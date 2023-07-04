package com.learning.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author: xwh90
 * @date: 2023/7/4 14:22
 * @description:
 */
@Slf4j
public class BufferTypeTest {

    //缓冲区测试用例
    @Test
    public void testHeapBuffer() {
        final ByteBuf heapBuffer = ByteBufAllocator.DEFAULT.heapBuffer();
        heapBuffer.writeBytes("疯狂创客圈:高性能学习社群".getBytes(StandardCharsets.UTF_8));
        if (heapBuffer.hasArray()) {
            final byte[] array = heapBuffer.array();
            final int offset = heapBuffer.arrayOffset();
            final int length = heapBuffer.readableBytes();
            final String result = new String(array, offset, length);
            System.out.println(result);
            heapBuffer.release();
        }
    }

    //缓冲区测试用例
    @Test
    public void testDirectBuffer() {
        final ByteBuf directBuffer = ByteBufAllocator.DEFAULT.directBuffer();
        directBuffer.writeBytes("疯狂创客圈:高性能学习社群".getBytes(StandardCharsets.UTF_8));
        if (!directBuffer.hasArray()) {
            final int length = directBuffer.readableBytes();
            final int index = directBuffer.readerIndex();
            final byte[] array = new byte[length];
            directBuffer.getBytes(index,array);
            final String result = new String(array);
            System.out.println(result);
            directBuffer.release();
        }
    }
}
