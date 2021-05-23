package com.demo.leetcode;

public class RobSolution2 {

    public static void main(String[] args) {
        RobSolution2 solution = new RobSolution2();
//        int[] nums ={1,2};
//        int[] nums ={1};
        int[] nums ={2,3,2};
        System.out.println(solution.rob(nums));
    }

    public int rob(int[] nums){
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        if(nums.length==2){
            return Math.max(nums[0],nums[1]);
        }

       return Math.max(robRange(nums,0,nums.length-1),robRange(nums,1,nums.length));
    }
     public int robRange(int[] nums,int start,int end){

        //前一个最大
        int first_max = nums[start];
        //前两个最大
        int second_max= 0 ;
        for (int i=start+1;i< end;i++) {
            int tmp_max = Math.max(first_max,second_max+nums[i]);
            second_max= first_max ;
            first_max = tmp_max;
        }
        return first_max;
    }
}
