package com.demo.leetcode;

import org.junit.Test;

/**
 * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
 * <p>
 * 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。
 * <p>
 * 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/buddy-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BuddyStringSolution {

    @Test
    public void main() {
//        System.out.println(buddyStrings("aba", "aba"));
        System.out.println(buddyStrings("abab", "baba"));
    }

    public boolean buddyStrings(String s, String goal) {
        //长度不等不能转换
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.equals(goal)) {
            //相同，判断是否有重复的字符
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                if (count[index] == 1) {
                    return true;
                } else {
                    count[index] = 1;
                }

            }
            return false;
        }
        int first = -1, second = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                if (first == -1) {
                    first = i;
                    continue;
                } else if (second == -1) {
                    second = i;
                    continue;
                }
                return false;
            }
        }
        //s==goal
        if (first != -1 && second != -1) {
            if (s.charAt(first) == goal.charAt(second) && s.charAt(second) == goal.charAt(first)) {
                return true;
            }
        }
        return false;
    }

}
