package com.demo.leetcode;

import org.junit.Test;

/**
 * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
 */
public class LowerCaseSolution {

    @Test
    public void test() {
        System.out.println(toLowerCase("LOVELY"));
    }

    public String toLowerCase(String s) {
        return s.toLowerCase();
    }
}
