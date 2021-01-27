package com.demo.nowcoder;

import java.util.Random;

public class Test2 {

    public static void main(String[] args) {
        Random random = new Random();
        int size= 1+random.nextInt(11000);
        System.out.println(size);
        int[] arr = new int[size];
        for(int i = 0 ;i<size;i++){
            int index = 1+random.nextInt(size);
            arr[index] = index;
        }


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
