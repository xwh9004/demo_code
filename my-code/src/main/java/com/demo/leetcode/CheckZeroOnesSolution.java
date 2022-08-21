package com.demo.leetcode;

/**
 * 给你一个二进制字符串 s 。如果字符串中由 1 组成的 最长 连续子字符串 严格长于 由 0 组成的 最长 连续子字符串，返回 true ；否则，返回 false 。
 * <p>
 * 例如，s = "110100010" 中，由 1 组成的最长连续子字符串的长度是 2 ，由 0 组成的最长连续子字符串的长度是 3 。
 * 注意，如果字符串中不存在 0 ，此时认为由 0 组成的最长连续子字符串的长度是 0 。字符串中不存在 1 的情况也适用此规则
 * <p>
 * 输入：s = "1101"
 * 输出：true
 * 解释：
 * 由 1 组成的最长连续子字符串的长度是 2："1101"
 * 由 0 组成的最长连续子字符串的长度是 1："1101"
 * 由 1 组成的子字符串更长，故返回 true 。
 */
public class CheckZeroOnesSolution {

    public static void main(String[] args) {
        CheckZeroOnesSolution checkZeroOnesSolution = new CheckZeroOnesSolution();
        String s = "1011001110010000";
        System.out.println(checkZeroOnesSolution.checkZeroOnes(s));
    }

    public boolean checkZeroOnes(String s) {
        if (s.length() == 0) {
            return false;
        }
        int count1 = 0, count0 = 0;
        int max1 = 0, max0 = 0;
        char start = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (start != s.charAt(i)) {
                max1 = count1 > max1 ? count1 : max1;
                max0 = count0 > max0 ? count0 : max0;
                start = s.charAt(i);
                count1=0;
                count0=0;
            }
            if ('1' == start) {
                count1++;
            } else {
                count0++;
            }
        }
        max1 = count1 > max1 ? count1 : max1;
        max0 = count0 > max0 ? count0 : max0;
        return max1 > max0;
    }
}
