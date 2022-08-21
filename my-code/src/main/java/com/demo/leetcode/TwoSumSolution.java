package com.demo.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整数数组 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * 。
 */
public class TwoSumSolution {


    @Test
    public void twoSumTest() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] two = twoSum(nums, target);
        Assert.assertEquals(0, two[0]);
        Assert.assertEquals(1, two[1]);
    }

    @Test
    public void threeSumTest() {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> result = threeSum2(nums);
        System.out.println(result);

    }

    public int[] twoSum(int[] nums, int target) {
        int[] two = new int[2];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    two[0] = i;
                    two[1] = j;
                    return two;
                }
            }
        }
        return two;
    }

    /**
     * 不重复的三元组
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = j + 1; k < len; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    if (nums[i] + nums[j] + nums[k] == target) {
                        List<Integer> qualified = new ArrayList<>();
                        qualified.add(nums[i]);
                        qualified.add(nums[j]);
                        qualified.add(nums[k]);
                        result.add(qualified);
                    }
                }
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int k = len-1;
                //寻找第三层的有边界
                while (j < k && nums[j] + nums[k] > target) {
                    k--;
                }
                if (j == k) {
                    break;
                }
                if (nums[j] + nums[k] == target) {
                    List<Integer> qualified = new ArrayList<>();
                    qualified.add(nums[i]);
                    qualified.add(nums[j]);
                    qualified.add(nums[k]);
                    result.add(qualified);
                }

            }
        }
        return result;
    }
}
