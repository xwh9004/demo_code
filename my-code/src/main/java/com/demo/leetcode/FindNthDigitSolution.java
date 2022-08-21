package com.demo.leetcode;

import org.junit.Test;

/**
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位数字。
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nth-digit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindNthDigitSolution {

    @Test
    public void test() {
        System.out.println(findNthDigit(1000000000));
    }


    public int findNthDigit(int n) {
        //计算出 n位数字，在k,k+1位之间的数中
        long sum = 0;
        long preSum = 0;
        int k = 1;

        sum = totalDigits(k);
        while (sum < n) {
            k++;
            preSum = sum;
            sum = totalDigits(k) + sum;
        }
        //
        if (n == preSum) {
            //刚好位于k位整数开始的
            return 0;
        } else {
            //位于k位整数
            long offset = (n - preSum) / k;
            int remind = (int)(n - preSum) % k;
            long num1 = getDigitStart(k) + offset;
            if(remind==0){
                //刚好是num1的前一个数的最后一位
                return getDigitIn(num1-1, k);
            }
            return getDigitIn(num1, remind);

        }

    }

    public int getDigitIn(long num, int index) {

        return String.valueOf(num).charAt(index-1) - '0';
    }

    public long getDigitStart(int n) {
        long pow = 1;
        for (int i = 0; i < n - 1; i++) {
            pow = pow * 10;
        }
        return pow;
    }

    public long getDigitEnd(int n) {
        return getDigitStart(n + 1) - 1;
    }

    /**
     * n位数最多有多少个数字
     *
     * @param n
     */
    public long totalDigits(int n) {
        long pow = 1;
        for (int i = 0; i < n - 1; i++) {
            pow = pow * 10;
        }

        return n * 9 * pow;
    }


}
