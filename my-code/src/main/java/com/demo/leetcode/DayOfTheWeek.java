package com.demo.leetcode;

import org.junit.Test;

/**
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 *
 * 输入为三个整数：day、month 和 year，分别表示日、月、年。
 *
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 *
 * 输入：day = 31, month = 8, year = 2019
 * 输出："Saturday"
 */
public class DayOfTheWeek {

    @Test
    public void test() {
        System.out.println(dayOfTheWeek(31,8,2019));
        System.out.println(dayOfTheWeek(18,7,1999));
        System.out.println(dayOfTheWeek(15,8,1993));

    }


    //1970 年 12月 31 日是星期四
    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        int days =0;
        //1970-year之间多少天
        for (int i=1971;i<year;i++){
            if(isLeap(i)){
                days+=366;
            }else {
                days+=365;
            }
        }
        //year中 1-month多少天
        for (int i=1;i<month;i++){
            days+=monthDays[i-1];
        }

        if(isLeap(year) && month>2){
            days+=day+1;
        }else {
            days+=day;
        }
       return week[(days+3)%7];

    }

    public boolean isLeap(int year){
        if(year%4==0 && year%100!=0 || year%400==0){
            return true;
        }
        return false;
    }
}
