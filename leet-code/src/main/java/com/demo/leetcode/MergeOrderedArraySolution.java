package com.demo.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class MergeOrderedArraySolution {

    @Test
    public void test(){
        int[] arr1={1,3,8,9,9,15,18,21};
        int[] arr2={3,6,7,9,9,13,15,18};
        printArray(merge(arr1,arr2));
    }
    public void printArray(int[] arr){
        for (int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    /**
     * 求两个有序数组的交集
     * 1，3，8，9，15，18，21
     * 3，6，7，9，13，17，18
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] merge(int[] arr1,int[] arr2){
        int len1 = arr1.length;
        int len2 = arr2.length;
        int len = len1+len2;
        int[] tmp = new int[len];
        int count = 0;
        int i=0;int j = 0;
        while(i<len1&&j<len2) {
            if (arr1[i]== arr2[j]) {
                tmp[count++] = arr1[i];
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                tmp[count++] = arr1[i];
                i++;
            } else {
                tmp[count++] = arr2[j];
                j++;
            }
        }
        if(i<len1){
          while (i<len1){
              tmp[count++] = arr1[i++];
          }
        }
        if(j<len2){
            while (j<len2){
                tmp[count++] = arr2[j++];
            }
        }
        return Arrays.copyOf(tmp,count);
    }
}
