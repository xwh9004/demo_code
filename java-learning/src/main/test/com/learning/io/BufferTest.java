package com.learning.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.nio.IntBuffer;

/**
 * @author: xwh90
 * @date: 2023/2/21 9:49
 * @description:
 */
@Slf4j
public class BufferTest {

    static IntBuffer intBuffer = null;

    @Before
    public void createTest() {

        intBuffer = IntBuffer.allocate(20);
        log.info("------ after allocate -------");
        printArgs();

    }

    private void printArgs() {
        log.info("position =" + intBuffer.position());
        log.info("limit =" + intBuffer.limit());
        log.info("capacity =" + intBuffer.capacity());
    }


    @Test
    public void putTest() {

        log.info("------ after put -------");

        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }
        printArgs();
    }


    @Test
    public void readTest() {
        flipTest();  //不能直接put后读取

        log.info("------ after read -------");

        for (int i = 0; i < 3; i++) {
            log.info("get index[{}] ={}", i, intBuffer.get());
        }
        printArgs();
    }

    @Test
    public void flipTest() {
        putTest();
        intBuffer.flip();
        log.info("------ after flip -------");
        printArgs();
    }
}
