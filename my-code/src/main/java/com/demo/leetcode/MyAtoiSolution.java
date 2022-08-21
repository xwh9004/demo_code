package com.demo.leetcode;

import org.junit.Test;

/**
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 */
public class MyAtoiSolution {

    @Test
    public void test() {
        System.out.println(myAtoi("   -42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
        System.out.println(myAtoi("+-12"));
        System.out.println(myAtoi(""));
        System.out.println(myAtoi("   +0 123"));

    }


    /**
     * 另解，自动机
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        //读取 前导空格
        char space = ' ';
        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            return 0;
        }
        char first = chars[0];
        int count = 0;
        char[] intChar = new char[s.length()];
        int j = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == space) {
                if(i-j==1){
                    j++;
                }else {
                    break;
                }
            } else {
                //判断输入的正负性
                if (chars[i] == '-' || chars[i] == '+') {
                    //是正负号
                    intChar[count++] = chars[i];
                } else if ('0' <= chars[i] && chars[i] <= '9') {
                    //不是正负号，但是是数字
                    intChar[count++] = chars[i];
                } else {
                    //不是正负号，也不是是数字,说明读到结尾了
                    break;
                }
            }
        }
        if (count < 1) {
            return 0;
        }
        long value = 0;

        boolean negative = intChar[0] == '-' ? true : false;
        for (int i = 0; i < count; i++) {
            if (i != 0 && (intChar[i] == '-' || intChar[i] == '+')) {
                break;
            }
            if (intChar[i] != '-' && intChar[i] != '+') {
                value = value * 10 + intChar[i] - '0';
                if (value > Integer.MAX_VALUE) {
                    break;
                }
            }
        }
        value = negative ? -value : value;
        if (value > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (value < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }
}
