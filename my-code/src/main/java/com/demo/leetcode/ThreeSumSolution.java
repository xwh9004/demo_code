package com.demo.leetcode;

import java.util.*;

/**
 * @author: xwh90
 * @date: 2024/8/9 22:27
 * @description: 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
 * 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
 * 请你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeSumSolution {

    public static void main(String[] args) {
        ThreeSumSolution sumSolution = new ThreeSumSolution();
        int[] nums = new int[]{-1, -1, -1, 0, 1, 2};
//        int[] nums = new int[]{0,1,1};
//        int[] nums = new int[]{0,0,0};
//        int[] nums = new int[]{0,0,0,0};
//        int[] nums = new int[]{-1, -1, -1, 0, 0, 1};
        final List<List<Integer>> lists = sumSolution.threeSum0(nums);
        System.out.println(lists);
    }

    public List<List<Integer>> threeSum0(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i - 1] == nums[i]) {
                //计算过，跳过
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> values = new ArrayList<>();
                    values.add(nums[i]);
                    values.add(nums[left]);
                    values.add(nums[right]);
                    result.add(values);
                    while (left < right && nums[left] == nums[++left]) ;
                    while (left < right && nums[right] == nums[--right]) ;
                } else if (sum < 0) {
                    //向右移动left指针，并跳过所有重复的num[left]
                    while (left < right && nums[left] == nums[++left]) ;
                } else {
                    while (left < right && nums[right] == nums[--right]) ;
                }
            }
        }
        return result;
    }

    /**
     * 示例 1：
     * <p>
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * 注意，输出的顺序和三元组的顺序并不重要。
     * 示例 2：
     * <p>
     * 输入：nums = [0,1,1]
     * 输出：[]
     * 解释：唯一可能的三元组和不为 0 。
     * 示例 3：
     * <p>
     * 输入：nums = [0,0,0]
     * 输出：[[0,0,0]]
     * 解释：唯一可能的三元组和为 0 。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int left = 0; left < nums.length; ) {
            int right = left + 1;

            while (right < nums.length) {
                int find = 0 - nums[left] - nums[right];
                if (map.containsKey(find)) {
                    final Integer index = map.get(find);
                    if (index > right) {
                        List<Integer> values = new ArrayList<>();
                        values.add(nums[left]);
                        values.add(nums[right]);
                        values.add(find);
                        result.add(values);
                    }
                    right = map.get(nums[right]) + 1;
                } else {
                    right = map.get(nums[right]);
                    right = right + 1;
                }

            }
            int leftMax = map.get(nums[left]);
            left = leftMax + 1;
        }
        return result;
    }
}
