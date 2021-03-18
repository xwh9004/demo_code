package com.demo.sort.util;

import java.util.Random;

import static com.demo.sort.util.QuickSort.partition;

public class SortUtil {
    static int count =0;
    public static void main(String[] args) {
        Random  random =new Random();
        int size = 10;
        int[] array = new int[size];
        for (int i=0;i<size;i++){
            array[i] = random.nextInt(100);
        }

// int[] array1 = {74 ,11 ,55 ,50 ,71 ,64 ,47, 46, 40 ,76};
        int[] array1 = {11,8,9,3,7,1,2,5};
        System.out.print("数组：");
        printArray(array1);
        SortUtil.quick_sort(array1,0,array1.length-1);
        System.out.println(SortUtil.isSorted(array1));
    }


    public static void quick_sort(int[] array,int low,int high){
        if(low>=high){
            return;
        }
        int index =  partition(array,low,high);
        System.out.print((++count)+" 轮：");
        printArray(array);
        quick_sort(array,low,index-1);
        quick_sort(array,index+1,high);

    }


    public static int partition(int[] array,int low,int high){
        int k = array[low];
        int pl = low;
        int ph = high;
        while (true){
            while (pl<high && array[pl]<=k){
                pl++;
            }
            while (ph>low&& array[ph]>k){
                ph--;
            }
            if(pl>=ph){
                break;
            }
            swap(array,pl,ph);
        }
        swap(array,low,ph);
        return pl;

    }
    public static void merge_sort(int[] array,int low,int high){

        if(low>=high){
            return;
        }
        int mid = low + (high -low)/2;
        merge_sort(array,low,mid);
        merge_sort(array,mid+1,high);
        merge(array,low,mid,mid+1,high);
        System.out.print((++count)+" 轮：");
        printArray(array);
    }

    private static void merge(int[] array, int left_low,int left_high,int right_low, int right_high) {
          int[] tmp = new int[right_high - left_low+1];
          int i =0;
          int lp = left_low;
          int rp = right_low;
          while (lp<=left_high && rp<=right_high){
              if(array[lp]<array[rp]){
                  tmp[i++]=array[lp++];
              }else{
                  tmp[i++]=array[rp++];
              }
          }
          int start =0;
          int end =0;
          if(lp>left_high){
              start = lp;
              end = left_high;
          }else{
              start = rp;
              end = right_high;
          }
            while (start<=end){
             tmp[i++]=array[start];
             start++;
            }

        copyOf(tmp,array,0,left_low,tmp.length);
    }

    private static void copyOf(int[] src,int[] des,int s_start,int d_start,int length){
        for (int i=0;i<length;i++){
            des[d_start+i] = src[s_start+i];
        }
    }


    public static boolean isSorted(int[] array){
        for (int i=0;i<array.length-1;i++){
            if(array[i]>array[i+1]) {
                return false;
            }
        }
        return true;
    }

    //注意j的界限
    public static int[] insertSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            printArray(array);
           for (int j = i;j>=0;j--){
               if(array[j]>array[j+1]){
                   swap(array,j,j+1);
               }
           }
        }
        return array;
    }
    public static int[] bubbleSort(int[] array){
        for(int i=0;i<array.length;i++){
            printArray(array);
            int minIndex = i;
            for (int j=i+1;j<array.length;j++){
                if(array[minIndex]>array[j]){
                    minIndex = j;
                }
            }
            swap(array,i,minIndex);
        }
        return array;
    }
    public static int[] selectSort(int[] array){
        for(int i=0;i<array.length;i++){
            printArray(array);
            int minIndex = i;
            for (int j=i+1;j<array.length;j++){
                if(array[minIndex]>array[j]){
                    minIndex = j;
                }
            }
            swap(array,i,minIndex);
        }
        return array;
    }


    private static void swap(int[] array, int m, int n) {
        int tmp = array[m];
        array[m] = array[n];
        array[n] = tmp;
    }

    public static void printArray(int[] arr){
        for (int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
