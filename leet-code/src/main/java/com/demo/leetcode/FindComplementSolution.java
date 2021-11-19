package com.demo.leetcode;

import org.junit.Test;

/**
 * 对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。
 */
public class FindComplementSolution {

    @Test
    public void test() {
        System.out.println(findComplement(5));
    }

    public int findComplement(int num) {
        int t = 1 << 31;
        while ( (t & num)!=0) {
            num |= t;
            t >>= 1;
        }
        return num;
    }
}
