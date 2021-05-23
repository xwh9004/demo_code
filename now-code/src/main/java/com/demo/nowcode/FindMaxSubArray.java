package com.demo.nowcode;

/**
 * 给定一个数组arr，返回子数组的最大累加和
 * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
 * 题目保证没有全为负数的数据
 * [要求]
 * 时间复杂度为O(n)，空间复杂度为O(1)
 */
public class FindMaxSubArray {

    public static void main(String[] args) {
        int[] arr = {31,-41,59,26,-53,58,97,-93,-23,84};
        System.out.println(maxSumOfSubarray(arr));
        System.out.println(maxSumOfSubArray_1(arr));
        System.out.println(maxSum4(arr));
//        System.out.println(maxSum(arr,0,arr.length-1));
    }

   //O(n)
    public static int maxSumOfSubArray_1(int[] arr){
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
    //O(n^2)
    public static int maxSumOfSubarray (int[] arr) {
        if(arr.length ==1){
            return arr[0];
        }
        int len =arr.length;
        int[] sumMax =  new int[arr.length];

        int max =arr[0];
        //查找单个元素最大值
        for (int i = 0;i<len;i++){
            sumMax[i] = arr[i];
            if(max<arr[i]){
                max= arr[i];
            }
        }
        //求得连续 i个元素的子集的和。
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

    public static int maxSumOfSubarray_3 (int[] arr) {
        int maxSoFar = 0;

        for (int i=0;i<arr.length;i++){
            int sum = 0;
            for (int j=i;j<arr.length;j++){
                sum += arr[j];
                maxSoFar = Math.max(maxSoFar,sum);
            }
        }
        return maxSoFar;
    }
    public static int maxSumOfSubarray_4 (int[] arr) {
        int maxSoFar = 0;
        //保存 arr[0..i-1]的和
        int[] cumarr = new int[arr.length+1];
        cumarr[0] = -1;
        for (int i =0;i<arr.length;i++){
            cumarr[i+1] = cumarr[i]+arr[i];
        }

        for (int i=1;i<arr.length;i++){
            for (int j=i;j<arr.length;j++){
                int sum = cumarr[j] - cumarr[i-1];
                maxSoFar = Math.max(maxSoFar,sum);
            }
        }
        return maxSoFar;
    }

    // nlogn
    public static int maxSum(int[] arr,int left,int right){
        if(left>right) return 0;
        if(left == right) return Math.max(0,arr[left]);

        int mid = (left + right)/2;
        int leftMax =0;
        int sum = 0;
        for (int i = mid;i>=left;i--){
            sum +=arr[i];
            leftMax = Math.max(leftMax,sum);
        }
        int rightMax =0;
        sum =0;
        for (int i = mid+1;i<=right;i++){
            sum +=arr[i];
            rightMax = Math.max(rightMax,sum);
        }
        return Math.max(leftMax+rightMax,Math.max(maxSum(arr, left,mid),maxSum(arr, mid+1,right)));
    }


    public static int maxSum4(int[] arr){
        int maxSoFar =0;
        int maxEndingHere =0;
        for (int i=0;i<arr.length;i++){
            maxEndingHere = Math.max(maxEndingHere+arr[i],0);
            maxSoFar = Math.max(maxEndingHere,maxSoFar);
        }
        return maxSoFar;
    }

}
