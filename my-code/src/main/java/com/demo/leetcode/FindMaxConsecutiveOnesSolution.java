package com.demo.leetcode;

/**
 * <p><b>Description:</b>
 * 给定一个二进制数组， 计算其中最大连续 1 的个数。
 * 输入：[1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 14:12 on 2021/3/25
 * @version V0.1
 * @classNmae FindMaxConsecutiveOnesSolution
 */
public class FindMaxConsecutiveOnesSolution {

    public static void main(String[] args) {
        FindMaxConsecutiveOnesSolution solution = new FindMaxConsecutiveOnesSolution();
        int[] nums ={1,0,1,1,0,1};
        System.out.println(solution.findMaxConsecutiveOnes(nums));
    }

    /**
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max =0;
        if(nums.length ==0){
            return 0;
        }
        int sum =0;
        for (int i=0;i<nums.length;i++){
           if(nums[i]==0){
               sum = 0;
           }else {
               sum+=nums[i];
           }
           max = Math.max(sum,max);
        }
        return max;
    }
}
