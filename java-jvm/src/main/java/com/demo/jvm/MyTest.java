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

    public int test(String name){
        switch (name){
            case "Java":
                return 100;
            case "Kotlin":
                return 200;
            default:return -1;
        }
    }
    public int test1(String name){
        switch (name){
            case "Aa":
                return 100;
            case "BB":
                return 200;
            default:return -1;
        }
    }
}
