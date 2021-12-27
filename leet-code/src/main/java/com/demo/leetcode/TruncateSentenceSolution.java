package com.demo.leetcode;

import org.junit.Test;

/**
 * 子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
 * <p>
 * 例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/truncate-sentence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TruncateSentenceSolution {


    @Test
    public void test() {
        String s = "Hello how are you Contestant";
        System.out.println(truncateSentence(s, 4));

    }

    public String truncateSentence(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int ct = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (s.charAt(i) == ' ') {
                ct++;
            }
            if (ct == k) {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
