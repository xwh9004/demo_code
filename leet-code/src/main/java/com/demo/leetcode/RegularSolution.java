package com.demo.leetcode;

import org.junit.Test;

/**
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 *
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串

 */
public class RegularSolution {


    @Test
    public void test(){
        System.out.println(isMatch("AAABCAAB","A*B.A*B"));
    }

    /**

     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {

        boolean[][] matchTable = new boolean[s.length()+1][p.length()+1];

        matchTable[0][0] = true;
        // p 与 空 匹配
        for (int col = 1; col < matchTable[0].length ; col++){
            char c = p.charAt(col-1);
            if(col > 1){
                if(c =='*'){
                    matchTable[0][col] = matchTable[0][col-2];
                }else{
                    matchTable[0][col] = false;
                }
            }else{
                if(c =='*'){
                    matchTable[0][col] = true;
                }
            }
        }
        //
        for (int row = 1; row < matchTable.length ; row++){
            char ch1 = s.charAt(row-1);
            for (int col = 1;col < matchTable[col].length;col++){
                char ch2 = p.charAt(col-1);
                if(ch1 == ch2 || ch2 == '.'){
                    matchTable[row][col] =  matchTable[row-1][col-1];
                }else if(ch2 == '*'){
                     if(col>1){
                         // * 前面的字符出现0次
                         if(matchTable[row][col-2]){
                             matchTable[row][col] =true;
                         }else{
                             // * 前面的字符出现多次
                             char prev = p.charAt(col-2);
                             if(ch1 == prev || prev == '.'){
                                 matchTable[row][col] =  matchTable[row-1][col];
                             }
                         }

                     }
                }
            }
        }
        return  matchTable[s.length()][p.length()];
    }
}
