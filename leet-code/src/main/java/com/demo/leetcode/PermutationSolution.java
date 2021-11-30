package com.demo.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案
 */
public class PermutationSolution {


    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute(nums);

        for (List<Integer> list : result) {
            System.out.print("[");
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println("]");
        }
    }


    public List<List<Integer>> permute(int[] nums) {
        return permute(nums, nums.length - 1);
    }

    public List<List<Integer>> permute(int[] nums, int index) {
        List<List<Integer>> result = new ArrayList<>();
        if (index == 0) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[index]);
            result.add(list);
            return result;
        }
        List<List<Integer>> tmp = permute(nums, index - 1);
        for (int i = 0; i < tmp.size(); i++) {
            List<Integer> subList = tmp.get(i);
            for (int j = 0; j <= subList.size(); j++) {
                List<Integer> newList = new ArrayList<>();
                newList.addAll(subList);
                newList.add(j, nums[index]);
                result.add(newList);
            }
        }
        return result;
    }
}
