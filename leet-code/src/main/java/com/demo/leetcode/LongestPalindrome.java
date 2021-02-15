package com.demo.leetcode;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome2("abb"));
    }
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }


    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }
        int index = 0;
        //index向右移动
        int maxLong = 0;
        int start = 0;
        while (index < len) {
           int len1 = expandAroundCenter(s,index,index);
           int len2 = expandAroundCenter(s,index,index+1);
           int maxLen = Math.max(len1,len2);
           if(maxLen>maxLong){
               maxLong = maxLen;
               start = index-(maxLong-1)/2;
           }
           index++;
        }
        return s.substring(start,maxLong+1);
    }

    public int expandAroundCenter(String s,int left,int right){
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right-left-1;
    }
}
