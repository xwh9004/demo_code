package com.learning.util;

import org.slf4j.helpers.MessageFormatter;

/**
 * @author: xwh90
 * @date: 2023/4/21 14:33
 * @description:
 */
public class Printer {

    public static void info(String format,Object... args){
        final String message = MessageFormatter.arrayFormat(format, args).getMessage();
        System.out.println(message);
    }
}
