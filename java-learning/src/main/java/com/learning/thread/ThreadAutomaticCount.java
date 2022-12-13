package com.learning.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: xwh90
 * @date: 2022/12/8 17:05
 * @description:
 */
public class ThreadAutomaticCount {
    private AtomicLong value;

    public long getValue() {
        return value.longValue();
    }

    public void incr(){
        value.incrementAndGet();
    }
}
