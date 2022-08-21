package com.demo.leetcode;

/**
 * 统计一个数字在排序数组中出现的次数。
 *

 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2

 *示例2
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 */
public class SearchTargetCountSolution {

    public static void main(String[] args) {
        SearchTargetCountSolution solution = new SearchTargetCountSolution();
//        int[] nums = {5,7,7,8,8,10};
//        int target = 6;
        int[] nums = {2,2};
        int target = 3;
        System.out.println(solution.search(nums,target));

    }
    public int search(int[] nums, int target) {
        if(nums.length == 0){
            return 0;
        }
        int start = findFirst(nums,target);
        if(start>-1){
            int end = findLast(nums,target);
            return end-start+1;
        }
        return start+1;
    }

    private int findFirst(int[] nums, int target) {
        int low =0;
        int high = nums.length-1;
        //查找第一个等于target的数的下标
        while (low<=high){
            int mid = (low + high)>>1;
            if(nums[mid]< target){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        int start = -1;
        if(low<nums.length && nums[low]== target){
            start = low;
        }
        return start;
    }

    private int findLast(int[] nums, int target) {
        int low =0;
        int high = nums.length-1;
        //查找第一个等于target的数的下标
        while (low<=high){
            int mid = (low + high)>>1;
            if(nums[mid]<=target){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        int end = -1;
        if(high<nums.length && nums[high]== target){
            end = high;
        }
        return end;
    }
}
