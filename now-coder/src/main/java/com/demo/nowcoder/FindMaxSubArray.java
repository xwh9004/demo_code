package com.demo.nowcoder;

/**
 * 给定一个数组arr，返回子数组的最大累加和
 * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
 * 题目保证没有全为负数的数据
 * [要求]
 * 时间复杂度为O(n)，空间复杂度为O(1)
 */
public class FindMaxSubArray {

    public static void main(String[] args) {
        int[] arr = {1, -2, 3, 5, -2, 6, -1};
        System.out.println(maxsumofSubarray(arr));
        System.out.println(maxSumOfSunArray(arr));
    }


    public static int maxSumOfSunArray(int[] arr){
        int max = 0;
        int sum = 0;
        if(arr.length==0){
            return 0;
        }
        for (int i = 0;i<arr.length;i++){
            if(sum<0){
                sum=arr[i];
            }else{
                sum+=arr[i];
            }
            if(sum>max){
                max =sum;
            }

        }
        return max;

    }

    public static int maxsumofSubarray (int[] arr) {
        if(arr.length ==1){
            return arr[0];
        }
        int len =arr.length;
        int[] sumMax =  new int[arr.length];

        int max =arr[0];
        // write code here
        for (int i = 0;i<len;i++){
            sumMax[i] = arr[i];
            if(max<arr[i]){
                max= arr[i];
            }
        }
        for (int i = 2;i<sumMax.length;i++){
            for(int j = 0;j<len - i+1;j++){
                sumMax[j] = sumMax[j] +arr[j+i-1];
                if(max< sumMax[j] ){
                    max=  sumMax[j] ;
                }
            }
        }
        return max;
    }
}
