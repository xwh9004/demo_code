package com.demo.leetcode;

/**
 * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 */
public class MaxPowerSolution {

    public static void main(String[] args) {
        MaxPowerSolution solution = new MaxPowerSolution();
        String s = "cc";
        System.out.println(solution.maxPower(s));
    }


    public int maxPower(String s) {
        if(s.length()==0){
            return 0;
        }
        int power = 1;
        int maxPower = power;

        char start = s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == start) {
                power++;
            } else {
                start = c;
                maxPower = Math.max(maxPower, power);
                power = 1;
            }
        }
        return  Math.max(maxPower, power);
    }
}
