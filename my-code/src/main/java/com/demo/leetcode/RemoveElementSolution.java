package com.demo.leetcode;

import org.junit.Test;

/**
 * @author: xwh90
 * @date: 2022/12/9 14:56
 * @description:
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveElementSolution {
    @Test
    public void test(){
//        int[] nums ={1,2,3,6,5,7,8,6,6,8,9};
//        int val = 6;
        //[4,4,0,1,0,2]
        //0
        int[] nums ={4,4,0,1,0,2};
        int val = 0;
        System.out.println(removeElement(nums,val));

    }

    /**
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int count =0;
        for (int i = 0; i < len -count; i++) {
            if(nums[i] == val){
              //将后面的元素往前移动一个位置
                int j = i;
                while (j <len -count -1){
                    nums[j] = nums[j+1];
                    j++;
                }
                nums[len -count-1] =0;
                count++;
                i--;
            }
        }
        return len - count;
    }
    public int removeElement0(int[] nums, int val) {
        if (nums == null || nums.length == 0)
            return 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

    public int removeElement1(int[] nums, int val) {
        if (nums == null || nums.length == 0)
            return 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}
