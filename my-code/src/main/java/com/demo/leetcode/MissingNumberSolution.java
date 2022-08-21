package com.demo.leetcode;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 示例 1:

 输入: [0,1,3]
 输出: 2
 示例 2:

 输入: [0,1,2,3,4,5,6,7,9]
 输出: 8
 */
public class MissingNumberSolution {

    public static void main(String[] args) {
        MissingNumberSolution solution = new MissingNumberSolution();
        int[] nums = {1};
        System.out.println(solution.missingNumber(nums));
        int[] nums2 = {0,2};
        System.out.println(solution.missingNumber(nums2));
        int[] nums3 = {0,1,3};
        System.out.println(solution.missingNumber(nums3));
        int[] nums4 = {0,1,2,3,4,5,6,7,9};
        System.out.println(solution.missingNumber(nums4));

    }

    public int missingNumber(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int low =0;
        int high = nums.length-1;
        while (low<=high){
            int mid = (low + high)/2;
            if(nums[mid]==mid){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return low;
    }
}
