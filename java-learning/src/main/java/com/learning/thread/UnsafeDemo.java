package com.learning.thread;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: xwh90
 * @date: 2022/12/9 17:00
 * @description:
 */
public class UnsafeDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = getUnsafeInstance();
        System.out.println("start...."+ System.currentTimeMillis());
        //绝对时间
        unsafe.park(true, System.currentTimeMillis()+30000);
        System.out.println("end...."+ System.currentTimeMillis());


    }

    //使用方法
    private static Unsafe getUnsafeInstance() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }
}
