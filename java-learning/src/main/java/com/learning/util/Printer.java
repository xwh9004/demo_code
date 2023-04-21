package com.learning.util;

/**
 * @author: xwh90
 * @date: 2023/4/21 14:33
 * @description:
 */
public class Printer {

    public static void info(String format,Object... args){
        String info = String.format(format,args);
        System.out.println(info);
    }
}
