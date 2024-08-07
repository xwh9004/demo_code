package com.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xwh90
 * @date: 2024/8/6 21:57
 * @description: 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(" "));//5
        System.out.println(lengthOfLongestSubstring("abcded"));//5
        System.out.println(lengthOfLongestSubstring("123a3bc1edd")); //7
        System.out.println(lengthOfLongestSubstring("pwwkew"));//3
        System.out.println(lengthOfLongestSubstring("bbbbb"));//1
        System.out.println(lengthOfLongestSubstring("abcabcbb"));//3

    }

    public static int lengthOfLongestSubstring(String s) {
        if (s ==null ||s.length()==0){
            return 0;
        }
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int p1 = 0, p2 = 0;
        map.put(s.charAt(p1), p1);
        int max = 1;
        for (int i = 1; i < n; i++) {
            p2 = i;
            char c2 = s.charAt(p2);
            if (map.containsKey(c2)) {
                final Integer index = map.get(c2);
                while (p1 <= index) {
                   map.remove(s.charAt(p1++));
                }
            }
            map.put(s.charAt(p2),p2);
            max =Math.max(max,map.size());
        }
        return max;
    }
}
