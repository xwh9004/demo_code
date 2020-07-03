package com.example.redis.client;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 11:16 on 2020/6/19
 * @version V0.1
 * @classNmae MainTest
 */
public class MainTest {

    public static void main(String[] args) {

        foo(1);
    }



    public static void foo(Object obj) {
        int sum = 0;
        for (int i = 0; i < 200; i++) {
            sum += i;
        }
    }


}
