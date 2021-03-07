package com.demo.jvm;

/**
 * javaagent局限性 在执行main方法之前运行premain
 * -javaagent:D:\gitRepo\demo_code\java-aop\target\java-aop-1.0-SNAPSHOT.jar
 */
public class MyTest {

    public static void main(String[] args) {
        new MyTest().foo();
    }

    private void foo() {
        bar1();
        bar2();
    }

    private void bar1() {
    }

    private void bar2() {
    }
}
