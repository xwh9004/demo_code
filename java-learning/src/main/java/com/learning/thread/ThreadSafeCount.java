package com.learning.thread;

/**
 * @author: xwh90
 * @date: 2022/12/8 17:05
 * @description:
 */
public class ThreadSafeCount {
    private  long value;

    private Object lock = new Object();

    public synchronized long getValue() {
        return value;
    }

    public synchronized void incr(){
        ++value;
    }

    public  void incr0(){
        synchronized (lock){
            ++value;
        }
    }
}
