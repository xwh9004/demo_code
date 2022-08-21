package com.demo.leetcode;

import java.util.Random;


/**
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 * <p>
 * 实现 Solution class:
 * <p>
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shuffle-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    private int[] originNums;
    private int[] nums;

    public Solution(int[] nums) {
        this.originNums = nums;
        this.nums = nums;
    }

    public int[] reset() {
        return originNums;
    }

    public int[] shuffle() {
        int[] used = new int[originNums.length];
        int[] shuffle = new int[originNums.length];
        Random random = new Random();
        for (int i = 0; i < originNums.length; i++) {
            boolean flag = true;
            while (flag) {
                int index = random.nextInt(originNums.length);
                if (used[index] == 0) {
                    used[index] = 1;
                    shuffle[i] = originNums[index];
                    flag = false;
                }
            }

        }

        return shuffle;
    }

    public int[] fisherYatesShuffle() {
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            int j = i + random.nextInt(nums.length - i);
            swap(nums, i, j);
        }

        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Solution solution = new Solution(nums);
        int[] shuffle = solution.shuffle();
        for (int i : shuffle) {
            System.out.println(i);
        }

    }
}
