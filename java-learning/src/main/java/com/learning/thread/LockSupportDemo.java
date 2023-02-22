package com.learning.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: xwh90
 * @date: 2023/1/5 17:20
 * @description:
 */
public class LockSupportDemo {

    public static void main(String[] args) throws InterruptedException {
//        parkTest();

        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("child thread begin park");
                LockSupport.park();
                System.out.println("child thread unpark");
            }
        };

        thread.start();

        Thread.sleep(1000);

        System.out.println("main thread begin park");

        LockSupport.unpark(thread);
    }

    private static void parkTest() {
        System.out.println("begin park !");
        // 使当前线程获取到许可证
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("end park ");
    }
}
