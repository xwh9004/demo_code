package com.learning.thread;

/**
 * @author: xwh90
 * @date: 2022/12/8 17:01
 * @description:
 */
public class ThreadUnSafeCount {
    private long value;

    public long getValue() {
        return value;
    }

    public void incr(){
        ++value;
    }
}
