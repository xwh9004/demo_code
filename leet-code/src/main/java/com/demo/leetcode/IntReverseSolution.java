package com.demo.leetcode;

/**
 * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0

 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 *
 * 输入：x = 0
 * 输出：0
 *
 */
public class IntReverseSolution {

    public static void main(String[] args) {

        IntReverseSolution solution = new IntReverseSolution();
        //2147483647
        System.out.println(solution.reverse(Integer.MAX_VALUE));
        System.out.println(solution.reverse(123));
        System.out.println(solution.reverse(-123));
        System.out.println(solution.reverse(120));
        System.out.println(solution.reverse(-0));

        System.out.println(solution.reverse(1534236469));

    }

    public int reverse(int x) {
        int divide = x;
        if(divide<0){
            divide = Math.abs(divide);
        }
        int reverse = 0;
        int  mod = 0;
        while (divide!=0){
            mod = divide%10;
            int temp = reverse*10 + mod;
            if(reverse == (temp-mod)/10){
                reverse = reverse*10 + mod;
            }else {
                reverse = 0;
                break;
            }
            divide =divide/10;
        }
       return x>0?reverse:-reverse;
    }
}
