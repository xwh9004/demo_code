package com.demo.leetcode;


import org.junit.Test;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * <p>
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * <p>
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargestSumAfterKNegationsSolution {


    @Test
    public void test() {

        int[] nums = {-8, 3, -5, -3, -5, -2};
        int k = 6;
        System.out.println(largestSumAfterKNegations(nums, k));

    }


    public int largestSumAfterKNegations(int[] nums, int k) {
        //从小到大排序
        Arrays.sort(nums);
        int ct = 0;
        int minIndex =0;
        int len = Math.min(nums.length, k);
        for (int i = 0; i < len; i++) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
                minIndex =ct;
                ct++;

            } else {
                if (nums[i] == 0) {
                    //存在0 可以将反转剩余的次数
                    ct = k;
                    break;
                }
            }
        }
        if (ct < k) {
            //还有没反转完的次数
            int r = k - ct;
            //此时都为正数，需要比较 minIndex 与
            if (r % 2 == 1) {
                int min1 = nums[minIndex];
                int nextIndex = ct >= nums.length - 1 ? nums.length - 1 : ct;
                int min2 = nums[nextIndex];
                if (min1 < min2) {
                    //不断反转ct
                    nums[minIndex] = -nums[minIndex];
                } else {
                    //不断反转ct+1
                    nums[nextIndex] = -nums[nextIndex];
                }
            }
        }
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        return sum;
    }
}
