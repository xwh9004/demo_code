package com.demo.leetcode;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 注意数组中可能存在重复的元素。
 */
public class ReverseFindMin {

    public static void main(String[] args) {
       int[] array ={2,2,2,0,1};
        System.out.println(findMin(array));

    }

    public static int findMin(int[] nums) {
        int len =nums.length-1;
        int mid = 0;
        int low = 0;
        int high =len;
        while (low<high){
             mid = (low + high)/2;
             if(nums[mid]>nums[high]){
                 low = mid+1  ;
             }else if(nums[mid]<nums[high]) {
                 high = mid ;
             }else {
                 high--;
             }
        }

         return nums[low];
    }
}
