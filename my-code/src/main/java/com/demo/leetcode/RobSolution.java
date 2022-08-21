package com.demo.leetcode;

public class RobSolution {

    public static void main(String[] args) {
        RobSolution solution = new RobSolution();
//        int[] nums ={1,2};
//        int[] nums ={1};
        int[] nums ={1,2,9,1,1,10,6};
        System.out.println(solution.rob1(nums));
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
     public int rob1(int[] nums){
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        //前一个最大
        int first_max = nums[0];
        //前两个最大
        int second_max= 0 ;
        for (int i=1;i< nums.length;i++) {
            int tmp_max = Math.max(first_max,second_max+nums[i]);
            second_max= first_max ;
            first_max = tmp_max;
        }
        return first_max;
    }
}
