package com.demo.leetcode;


import org.junit.Test;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 */
public class IntPalindromeSolution {

    @Test
    public void test(){
        System.out.println(isPalindrome(123321));
    }

    public boolean isPalindrome(int x) {
          if(x<0){
              return false;
          }
          return x==reverse(x);
    }
    public int reverse(int divide) {
        int reverse = 0;
        do {
            reverse = reverse*10 + divide%10;
            divide = divide/10;
        }while (divide!=0);
        return reverse;
    }
}
