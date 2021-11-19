package com.demo.leetcode;

import org.junit.Test;

/**
 * 给定一个正整数n ，你可以做如下操作：
 * <p>
 * 如果n是偶数，则用n / 2替换n 。
 * 如果n是奇数，则可以用n + 1或n - 1替换n 。
 * n变为 1 所需的最小替换次数是多少？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntegerReplacementSolution {

    /**
     * 1024 + 210
     * 10   +  128
     */

    @Test
    public void test() {
//        System.out.println(integerReplacement(1234));
//        System.out.println(integerReplacement(65535));
//        System.out.println(integerReplacement(10000));
        System.out.println(integerReplacement(2147483647));
    }

    public int integerReplacement(int n) {
        return function(n);
    }

    public int function(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return function(n /2) + 1;

        } else {
            return Math.min(function(n/2), function(n/2 +1))+2;
        }
    }

}
