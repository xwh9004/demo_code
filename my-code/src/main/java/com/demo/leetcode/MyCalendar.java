package com.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xwh90
 * @date: 2024/8/11 20:41
 * @description:
 */
public class MyCalendar {
    List<int[]> schedules = new ArrayList<>();

    public MyCalendar() {

    }

    public boolean book(int start, int end) {
        for (int[] schedule : schedules) {
            int start_s = schedule[0];
            int end_s = schedule[1];
            if (!(start>=end_s || end<=start_s)) {
                return false;
            }
        }
        schedules.add(new int[]{start, end});
        return true;
    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(37, 50));
        System.out.println(myCalendar.book(33, 50));
        System.out.println(myCalendar.book(4, 17));
        System.out.println(myCalendar.book(35, 48));
        System.out.println(myCalendar.book(8, 25));
    }
}
