package com.demo.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author: xwh90
 * @date: 2024/8/12 21:46
 * @description: 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * <p>
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，
 * 注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。
 * <p>
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。
 * <p>
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 * <p>
 * 请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 */
public class MyCalendarTwo {

    TreeMap<Integer, Integer> calendarMap = new TreeMap<>();


    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        //start时间被预定的次数
        calendarMap.put(start, calendarMap.getOrDefault(start, 0) + 1);
        calendarMap.put(end, calendarMap.getOrDefault(end, 0) - 1);
        int overlaps = 0;
        int maxBooks = 0;
        for (Map.Entry<Integer, Integer> entry : calendarMap.entrySet()) {
            final Integer bookTimes = entry.getValue();
            overlaps = overlaps + bookTimes;
            maxBooks = Math.max(overlaps, maxBooks);
            if (overlaps > 2) {
                calendarMap.put(start, calendarMap.getOrDefault(start, 0) - 1);
                calendarMap.put(end, calendarMap.getOrDefault(end, 0) + 1);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        System.out.println(myCalendarTwo.book(10, 20));// returns true
        System.out.println(myCalendarTwo.book(50, 60)); // returns true
        System.out.println(myCalendarTwo.book(10, 40)); // returns true
        System.out.println( myCalendarTwo.book(5, 15)); // returns false
        System.out.println(myCalendarTwo.book(5, 10)); // returns true
        System.out.println(myCalendarTwo.book(25, 55)); // returns true
    }


}
