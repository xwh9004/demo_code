package com.demo.sort.util;

import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {

       int size =10000000;
       int[] a = new int[size];
       Random random = new Random();
       for (int i = 0;i<size;i++){
           a[i] = random.nextInt(size);
       }
//       int[] a1 ={5,6,3,2,0,6,3,0,5,5};
//        printArray(a);
        long start = System.currentTimeMillis();
        sort(a,0,a.length-1);
//        printArray(a);
        System.out.println("耗时："+(System.currentTimeMillis() - start) +"ms");
        System.out.println(a[0]);
        System.out.println(a[size-1]);
    }

    public static void printArray(int[] a){
        for (int i:a){
            System.out.print(i);
            System.out.print(",");
        }
        System.out.println();
    }
    public static void sort(int[] a,int start,int end){
        if(end<=start){
            return;
        }
        int index =partition(a,start,end);
        sort(a,start,index-1);
        sort(a,index+1,end);
    }

    public static int partition(int[] a,int low,int high){
        int k = a[low];
         //将数组一分为2，左边都比 k小，右边都比k大
        int pl = low+1;
        int ph = high;
        while (true){
            //左边部分 第一个比k大的数
            while (a[pl]<=k){
                pl++;
                if(pl>=high){
                    break;
                }
            }
            //右边比分 第一个k小的数
            while (a[ph]>k){
                ph--;
                if(ph<=low){
                    break;
                }
            }
            if(pl>=ph){
                break;
            }
            swap(a,pl,ph);
        }
        swap(a,low,ph);
        return ph;
    }

    public static void swap(int[] a,int i,int j){
        int tmp=a[i];
        a[i] = a[j];
        a[j] =tmp;
    }
}
