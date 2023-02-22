package com.learning.thread;

/**
 * @author: xwh90
 * @date: 2022/12/12 10:25
 * @description: jvm 指令重排序验证
 */
public class JVMReorderTest {

    private static int num;
    private static boolean ready;

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();

        WriteThread writeThread = new WriteThread();
        writeThread.start();

        Thread.sleep(10);
        readThread.interrupt();

        System.out.println("main exit");
    }

    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready && num + num == 0) {
                    System.out.println(num + num);
                    System.out.println("read thread");
                }

            }
        }
    }

    public static class WriteThread extends Thread {
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("write thread set over");
        }
    }
}
