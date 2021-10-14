package com.demo.leetcode;

/**
 * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：
 * <p>
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [0,1,0]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：arr = [1,3,5,4,2]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：arr = [0,10,5,2]
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：arr = [3,4,5,1]
 * 输出：2
 * 示例 5：
 * <p>
 * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
 * 输出：2
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= arr.length <= 10^4
 * 0 <= arr[i] <= 10^6
 * 题目数据保证 arr 是一个山脉数组
 *  
 * <p>
 * 进阶：很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/B1IidL
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PeakIndexInMountainArraySolution {

    public static void main(String[] args) {
        PeakIndexInMountainArraySolution solution = new PeakIndexInMountainArraySolution();
//        int[] a = {0, 10, 5, 2};
//        //index = 1
//        System.out.println(solution.peakIndexInMountainArray(a));
//        int[] a1 = {1,3,5,4,2};
//        //index = 2
//        System.out.println(solution.peakIndexInMountainArray(a1));
//        int[] a2 = {24, 69, 100, 99, 79, 78, 67, 36, 26, 19};
//        //index = 2
//        System.out.println(solution.peakIndexInMountainArray(a2));
//        int[] a3 = {18, 29, 38, 59, 98, 100, 99, 98, 90};
//        //index = 5
//        System.out.println(solution.peakIndexInMountainArray(a3));
        int[] a4 = {4,5,3,2,0};
        //index = 1
        System.out.println(solution.peakIndexInMountainArray(a4));
    }

    private int peakIndexInMountainArray(int[] a) {

        int left = 1;
        int right = a.length - 1;
        int mid =0;
        while (left <= right) {
             mid = (left + right) >> 1;
            if (a[mid] < a[mid - 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 官方解题
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray0(int[] arr) {
        int n = arr.length;
        int left = 1, right = n - 2, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
