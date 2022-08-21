package com.demo.leetcode;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] num1 = {1};
        int[] num2 = {1,3,5};
        System.out.println(findMedianSortedArrays1(num1, num2));
    }

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        //保证 m2更大
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays1(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int i = 0;
        int j =(m+n+1)/2 - i;
        //总长度偶数
        int left = 0;
        int right = m;
        int median1 =0,median2=0;
        while (left <= right) {
           i = (left + right) / 2;
           j = (m + n+1) / 2 - i;
           //边界处理
           int num_m1 = (i==0)?Integer.MIN_VALUE:nums1[i-1];
           int num_m = (i==m)?Integer.MAX_VALUE:nums1[i];
           int num_n1 = (j==0)?Integer.MIN_VALUE:nums2[j-1];
           int num_n = (j==n)?Integer.MAX_VALUE:nums2[j];
           if (num_m1< num_n1) {
                median1 = Math.max(num_m1,num_n1);
                median2 =Math.min(num_m,num_n);
                left = i +1 ;
           } else {
                right = i -1;
           }
        }


        System.out.println(i + "," + j);

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;

    }


    // O(m+n)
    public static double findMedianSortedArrays_1(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0) {
            return findMedianSortedArrays1(nums2);
        }
        if (len2 == 0) {
            return findMedianSortedArrays1(nums1);
        }
        int[] num = new int[len1 + len2];
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2) {
            //合并两个数组
            if (nums1[i] <= nums2[j]) {
                num[k++] = nums1[i];
                i++;
            } else {
                num[k++] = nums2[j];
                j++;
            }
        }
        if (i == len1) {
            //num1都合并到num中
            while (j < len2) {
                num[k++] = nums2[j++];
            }
        }
        if (j == len2) {
            //num1都合并到num中
            while (i < len1) {
                num[k++] = nums1[i++];
            }
        }
        return findMedianSortedArrays1(num);

    }

    public static double findMedianSortedArrays1(int[] nums) {

        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 0) {
            return 0;
        }
        double result = 0.0;

        int midIndex = len / 2;
        if (len % 2 == 0) {
            //偶数个值 example [1,2,3,4]
            result = (nums[midIndex - 1] + nums[midIndex]) / 2.0;
        } else {
            //基数个值 example[1,2,3,4,5]
            result = nums[midIndex];

        }
        return result;


    }

}
