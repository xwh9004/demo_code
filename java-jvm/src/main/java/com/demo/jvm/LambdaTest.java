package com.demo.jvm;

public class LambdaTest {

    public static void main(String[] args) {
        Runnable r = ()->{
            System.out.println("Hello Lambda");
        };
        r.run();
        System.out.println(r.getClass().getName());
    }
}
