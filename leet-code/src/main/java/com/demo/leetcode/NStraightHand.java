package com.demo.leetcode;

import java.util.Arrays;

/**
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 * <p>
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * 输出：true
 * 解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 示例 2：
 * <p>
 * 输入：hand = [1,2,3,4,5], groupSize = 4
 * 输出：false
 * 解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
 */
public class NStraightHand {

    public static void main(String[] args) {
        NStraightHand straightHand = new NStraightHand();
        test1(straightHand);
        test2(straightHand);
        test3(straightHand);


    }

    public static void test1(NStraightHand straightHand){
        int[] hand = {1,2,3,6,2,3,4,7,8};
        int groupSize = 3;
        System.out.println(straightHand.isNStraightHand(hand, groupSize));
    }
    public static void test2(NStraightHand straightHand){
        int[] hand = {1,2,3,4,5,6};
        int groupSize = 2;
        System.out.println(straightHand.isNStraightHand(hand, groupSize));
    }
    public static void test3(NStraightHand straightHand){
        int[] hand = {8,10,12};
        int groupSize = 3;
        System.out.println(straightHand.isNStraightHand(hand, groupSize));
    }
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int len = hand.length;
        if (len % groupSize != 0) {
            return false;
        }
        //对手中的牌排序
        Arrays.sort(hand);
        int rows = len / groupSize;
        //将牌逐一放置到group中
        int[][] groupArray = new int[rows][groupSize];
        int[] holdSize = new int[rows];
        for (int i = 0; i < len; i++) {
            //获取即将被放置的group
            int value = hand[i];
            int j = 0;
            for (; j < holdSize.length; j++) {
                if (holdSize[j] != 0) {
                    //groupArray 第 j行 被放置了牌
                    if (holdSize[j] < groupSize) {
                        //第j行没放满
                        if (groupArray[j][holdSize[j] - 1] + 1 == value) {
                            //第j行最后一张牌刚好被要放置的牌小1，可以放置
                            break;
                        }
                    }
                } else {
                    // 第 j行 没放置了牌
                    break;
                }
            }
            if(j>= holdSize.length){
                return false;
            }
            //牌被放置在第j行
            groupArray[j][holdSize[j]] = value;
            holdSize[j] = holdSize[j] + 1;
        }
        for (int i=0;i<holdSize.length;i++){
            if(holdSize[i]!=groupSize){
                return false;
            }
        }
        return true;
    }

}
