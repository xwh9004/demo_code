package com.learning.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author: xwh90
 * @date: 2023/4/21 14:18
 * @description:
 */
public class DataTimeUtil {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");

    public static String now() {
        final LocalDate now = LocalDate.now();
        return now.format(formatter);
    }
}
