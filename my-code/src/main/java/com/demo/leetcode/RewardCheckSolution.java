package com.demo.leetcode;

/**
 * <p><b>Description:</b>
 * 给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
 *
 * 'A' : Absent，缺勤
 * 'L' : Late，迟到
 * 'P' : Present，到场
 * 如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。
 *
 *
 * @author created by Jesse Hsu at 16:05 on 2021/3/26
 * @version V0.1
 * @classNmae RewardCheckSolution
 */
public class RewardCheckSolution {

    public static void main(String[] args) {
        RewardCheckSolution solution = new RewardCheckSolution();
        System.out.println(solution.checkRecord("PPALLP"));
        System.out.println(solution.checkRecord("PPALLL"));
    }

    public boolean checkRecord(String s) {
        //两个连续L
            return s.indexOf("LLL")<0 && s.indexOf("A")==s.lastIndexOf("A");
    }
}
