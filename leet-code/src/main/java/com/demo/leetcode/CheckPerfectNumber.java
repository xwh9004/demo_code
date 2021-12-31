package com.demo.leetcode;

import org.junit.Test;

public class CheckPerfectNumber {

    @Test
    public void test() {
        System.out.println(checkPerfectNumber(1));
        System.out.println(checkPerfectNumber(2));
        System.out.println(checkPerfectNumber(28));
        System.out.println(checkPerfectNumber(6));
        System.out.println(checkPerfectNumber(496));
        System.out.println(checkPerfectNumber(8128));
    }


    public boolean checkPerfectNumber(int num) {
        if (num <= 1) {
            return false;
        }
        int sum = 1;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        if (sum == num) {
            return true;
        }
        return false;

    }
}
