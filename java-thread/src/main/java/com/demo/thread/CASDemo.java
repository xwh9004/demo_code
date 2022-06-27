package com.demo.thread;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CASDemo {

    public static void main(String[] args) {
        normalCalculate();
        runIncrementWithLock();
        runIncrementCas();
    }

    public static void normalCalculate() {
        long counter = 0;
        long max = 500000000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        while (counter < max) {
            counter++;
        }
        stopWatch.suspend();
        System.out.println("耗时:" + stopWatch.getNanoTime() / (1000 * 1000) + "ms");
    }

    private static void runIncrementWithLock() {
        Lock lock = new ReentrantLock();
        long counter = 0;
        long max = 500000000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        while (counter < max) {
            if (lock.tryLock()) {
                counter++;
            }
        }
        stopWatch.suspend();
        System.out.println("耗时:" + stopWatch.getNanoTime() / (1000 * 1000) + "ms");
    }

    private static void runIncrementCas(){
        AtomicLong counter = new AtomicLong();
        long max = 500000000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        while (counter.incrementAndGet() < max) {
        }
        stopWatch.suspend();
        System.out.println("耗时:" + stopWatch.getNanoTime() / (1000 * 1000) + "ms");
    }
}
