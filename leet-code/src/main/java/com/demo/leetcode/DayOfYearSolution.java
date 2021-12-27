package com.demo.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class DayOfYearSolution {


    @Test
    public  void test() {
        DayOfYearSolution solution = new DayOfYearSolution();
        System.out.println();
        Assert.assertEquals(9,solution.dayOfYear("2019-01-09"));
        Assert.assertEquals(41,solution.dayOfYear("2019-02-10"));
        Assert.assertEquals(60,solution.dayOfYear("2003-03-01"));
        Assert.assertEquals(61,solution.dayOfYear("2004-03-01"));
    }

    public int dayOfYear(String date) {
        int[] daysOfMonthNonLeap = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] daysOfMonthLeap    = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] dates = date.split("-");
        String yearStr = dates[0];
        String monthStr = dates[1];
        String dayStr = dates[2];
        int year = Integer.valueOf(yearStr).intValue();
        int month = Integer.valueOf(monthStr).intValue();
        int day = Integer.valueOf(dayStr).intValue();
        int dayOfYear = 0;
        if (year % 4 == 0 || year % 100 == 0) {
            //闰年
            for (int i = 1; i < month; i++) {
                dayOfYear += daysOfMonthLeap[i - 1];
            }
            dayOfYear += day;

        } else {
            //平年
            for (int i = 1; i < month; i++) {
                dayOfYear += daysOfMonthNonLeap[i - 1];
            }
            dayOfYear += day;
        }
        return dayOfYear;
    }
}
