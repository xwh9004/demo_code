package com.demo.leetcode;

import org.junit.Test;

/**
 * 1995. 统计特殊四元组
 * 给你一个 下标从 0 开始 的整数数组 nums ，返回满足下述条件的 不同 四元组 (a, b, c, d) 的 数目 ：
 * <p>
 * nums[a] + nums[b] + nums[c] == nums[d] ，且
 * a < b < c < d
 */
public class CountQuadrupletsSolution {

    @Test
    public void test() {
//        int[] nums = {1, 2, 3, 6};
//        System.out.println(countQuadruplets(nums));
//        int[] nums1 = {3, 3, 6, 4, 5};
//        System.out.println(countQuadruplets(nums1));
//        int[] nums2 = {1, 1, 1, 3, 5};
//        System.out.println(countQuadruplets(nums2));
        int[] nums3 = {28, 8, 49, 85, 37, 90, 20, 8};
        System.out.println(countQuadruplets(nums3));
    }


    /**
     * 时间复杂度O(n^4)
     * @param nums
     * @return
     */
    public int countQuadruplets(int[] nums) {
        //先将素组排序
//        Arrays.sort(nums);
        int count = 0;
        for (int m = 3; m < nums.length; m++) {
            int d = nums[m];
            //计算[0,i]子数组满足num[a]+num[b]+num[c]==num[d]的个数
            for (int i = 0; i < m; i++) {
                for (int j = i + 1; j < m; j++) {
                    for (int k = j + 1; k < m; k++) {
                        if (nums[i] + nums[j] + nums[k] == d) {
                            System.out.println(nums[i] +"+"+ nums[j] +"+"+nums[k]+"="+d);
                            count++;
                        }
                    }
                }
            }
        }

        return count;

    }
}
