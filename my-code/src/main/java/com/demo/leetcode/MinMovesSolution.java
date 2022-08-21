package com.demo.leetcode;


import org.junit.Test;

/**
 * 453. 最小操作次数使数组元素相等
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：3
 * 解释：
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * 示例 2：
 * <p>
 * 输入：nums = [1,1,1]
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinMovesSolution {

    @Test
    public void test() {
        int[] nums = {1,2,3};
        System.out.println(minMoves(nums));

    }

    /**
     * 假设你可以无误执行完整个流程，这样可以搭建流程执行前后的桥梁。
     *
     * 将操作流程前后的量进行分析，找到一个不变量，构成等式，进行求解。
     *
     * 根据题意，每次操作将会使 n - 1 个元素增加 1 。由于你执行了 k 次，所以一共会使 sum 增加 k * (n - 1) 。即操作结束后数组的和为 sum + k * (n - 1) 。
     *
     * 贪心部分：在整个流程的每次操作中，最小的那个值都会增加 1 。（贪心的证明在下个板块，建议最后看）
     *
     * 由贪心可知，经过k 步后， min 变为了 min + k ，也意味着此时数组的每一项都变为了 min + k ，所以操作结束后数组的和为 n * (min + k) 。
     *
     * 根据等量代换：
     *
     * sum + k * (n - 1) = n * (min + k)
     * sum + k * n - k = n * min + n * k
     * sum - k = n * min
     * k = sum - n * min
     * 所以，最短操作次数为 k = sum - n * min 。
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int sum =0;
        int min = nums[0];
        for (int n:nums){
            sum+=n;
            min = Math.min(min,n);
        }
        return sum - nums.length*min;
    }


}
