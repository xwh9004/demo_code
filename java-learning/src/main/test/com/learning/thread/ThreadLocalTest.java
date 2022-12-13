package com.learning.thread;

/**
 * @author: xwh90
 * @date: 2022/12/7 14:18
 * @description:
 */
public class ThreadLocalTest {

//    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
//        11 (2) 设置线希呈交量

//        threadLocal.set("Hello World");
//
//        Thread thread = new Thread(){
//           @Override
//           public void run() {
//               //4 子线程输出线寻呈交量
//               System.out.println("thread " + threadLocal.get());
//           }
//       };
//        thread.start();
//
//        System.out.println("main" + threadLocal.get());
//
//        11 (2) 设置线希呈交量

        inheritableThreadLocal.set("Hello World");

        Thread thread = new Thread(){
           @Override
           public void run() {
               //4 子线程输出线寻呈交量

               System.out.println("thread " + inheritableThreadLocal.get());
           }
       };
        thread.start();

        System.out.println("main" + inheritableThreadLocal.get());



    }
}
