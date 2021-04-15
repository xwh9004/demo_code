package com.demo.leetcode;

public class RobSolution {

    public static void main(String[] args) {
        RobSolution solution = new RobSolution();
        int[] nums ={1,2};
        System.out.println(solution.rob(nums));
    }

    public int rob(int[] nums){
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int[] robMax = new int[nums.length];
        robMax[0] = nums[0];
        robMax[1]=Math.max(nums[0],nums[1]);
        for (int i=2;i< nums.length;i++) {
            robMax[i] = Math.max(robMax[i-1],robMax[i-2]+nums[i]);
        }
       return robMax[nums.length -1];
    }
}
