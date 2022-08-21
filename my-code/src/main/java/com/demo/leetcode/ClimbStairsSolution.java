package com.demo.leetcode;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class ClimbStairsSolution {
    public static void main(String[] args) {
        ClimbStairsSolution solution = new ClimbStairsSolution();
        System.out.println(solution.recursiveClimbStairs(1));
        System.out.println(solution.recursiveClimbStairs(2));
        System.out.println(solution.recursiveClimbStairs(10));
        System.out.println(solution.climbStairs(10));
    }

    public int recursiveClimbStairs(int n) {

        if(n==1 || n==2){
            return n;
        }
       return recursiveClimbStairs(n-1)+ recursiveClimbStairs(n-2);
    }

    public int climbStairs(int n){
        if(n==1||n==2){
            return n;
        }
        //n-1
        int tmp_1 = 2;
        //n-2
        int tmp_2 = 1;
        int tmp =0;
        for (int i=3;i<=n;i++) {
            tmp = tmp_1+ tmp_2;
            tmp_2 = tmp_1;
            tmp_1 = tmp;

        }
        return tmp;
    }

}
