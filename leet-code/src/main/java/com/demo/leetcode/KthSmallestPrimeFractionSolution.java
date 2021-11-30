package com.demo.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 第 K 个最小的素数分数
 * <p>
 * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数  组成，且其中所有整数互不相同。
 * <p>
 * 对于每对满足 0 < i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
 * <p>
 * 那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-th-smallest-prime-fraction
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthSmallestPrimeFractionSolution {

    @Test
    public void test() {

        int[] arr ={1,2,3,5}; int k=3;
        int[] ints = kthSmallestPrimeFraction(arr,k);
        System.out.println(ints);
    }


    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        List<int[]> fractionList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int[] fractionAarry = new int[2];
                fractionAarry[0] = arr[i];
                fractionAarry[1] = arr[j];
                fractionList.add(fractionAarry);
            }
        }
        Collections.sort(fractionList, (x, y) -> x[0] * y[1] - x[1] * y[0]);
        return fractionList.get(k - 1);
    }


}
