package com.demo.sort.util;

import java.util.Random;

public class SortUtil {

    public static void main(String[] args) {
        Random  random =new Random();
        int size = 10;
        int[] array = new int[size];
        for (int i=0;i<size;i++){
            array[i] = random.nextInt(100);
        }

//
        int[] array1 = {74 ,11 ,55 ,50 ,71 ,64 ,47, 46, 40 ,76};
        System.out.println(SortUtil.isSorted(SortUtil.insertSort(array1)));
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
