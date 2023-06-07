package com.learning.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: xwh90
 * @date: 2023/4/21 14:18
 * @description:
 */

@Slf4j
public class DataTimeUtil {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static String now() {
        final LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    public static void main(String[] args) {
        log.info(now());
    }
}
