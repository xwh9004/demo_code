package com.demo.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 * <p>
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
 * <p>
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-ranks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：score = [5,4,3,2,1]
 * 输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * 解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。
 */
public class FindRelativeRanksSolution {

    @Test
    public void test() {
//        int[] scores = {10, 3, 8, 9, 4};
        int[] scores = {5,4,3,2,1};
        String[] ranks = findRelativeRanks(scores);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < ranks.length; i++) {
            sb.append(ranks[i]).append(",");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }


    public String[] findRelativeRanks(int[] score) {
        int[] scoreCopy = Arrays.copyOf(score, score.length);
        Arrays.sort(score);
        //
        Map<Integer, Integer> rankMap = new HashMap<>();
        int len = score.length;
        for (int i = 0; i < len; i++) {
            rankMap.put(score[i], len - i );
        }
        String[] ranks = new String[score.length];
        for (int i = 0; i < score.length; i++) {
            ranks[i] = getRandDesc(rankMap.get(scoreCopy[i]));
        }
        return ranks;
    }

    public String getRandDesc(int i) {
        if (i == 1) {
            return "Gold Medal";
        }
        if (i == 2) {
            return "Silver Medal";
        }
        if (i == 3) {
            return "Bronze Medal";
        }
        return String.valueOf(i);
    }
}
