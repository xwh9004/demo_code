package com.demo.leetcode;

import java.util.Arrays;

/**
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，
 * 找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，
 * 则最小的4个数字是1、2、3、4。
 */
public class TopKSolution {

    public static void main(String[] args) {
        TopKSolution solution = new TopKSolution();
        int[] arr ={0,0,1,2,4,2,2,3,1,4,};
        printArray(solution.solution(arr,8));


    }

    public int[] solution(int[] arr,int k){

        sort(arr,0,arr.length-1,k);
        return Arrays.copyOfRange(arr,0,k);
    }


    private void sort(int[] arr, int low, int high, int k) {
        //check k <arr.length
        if(high<=low){
            return ;
        }

        int index = partition(arr, low, high);
        if(index ==k){
            return ;
        }
        sort(arr, low, index - 1,k);
        sort(arr, index+1, high, k);

    }

    private int partition(int[] arr, int low, int high) {
        int tmp = arr[low];
        int p1 = low;
        int p2 = high+1;
        while (p1 < p2) {
            while (arr[++p1] <= tmp) if (p1 == high) break;
            while (arr[--p2] > tmp) if (p2 == low) break;
            if (p1 < p2) {
                swap(arr, p1, p2);
            }
        }
        swap(arr, low, p2);
        return p2;

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public static void printArray(int[] a){
        for (int i:a){
            System.out.print(i);
            System.out.print(",");
        }
        System.out.println();
    }
}
