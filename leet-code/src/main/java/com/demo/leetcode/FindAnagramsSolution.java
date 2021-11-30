package com.demo.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindAnagramsSolution {

    @Test
    public void test() {
        String s = "cbaebabacd";
        String p = "abc";
//        System.out.println(orderBy(s));
        System.out.println(findAnagrams(s, p));
    }


    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }
        int pl = p.length();
        p = orderBy(p);
        for (int i = 0; i <= s.length() - pl; i++) {
            String sub = s.substring(i, pl + i);
            if (p.equals(orderBy(sub))) {
                //前一个相等，比较该字串的后一个字符和字串首字符是否相同
                result.add(i);
            }
        }
        return result;
    }

    public String orderBy(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);

        return new String(chars);
    }

}
