package com.demo.leetcode;

import org.junit.Test;

/**
 *
 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

 输入：[1,8,6,2,5,4,8,3,7]
 输出：49
 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

 */
public class MaxAreaSolution {

    @Test
    public void test(){
        int[] height ={1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
        int[] height2 ={1,2,1};
        System.out.println(maxArea(height2)==maxArea1(height2));
    }


    public int maxArea(int[] height) {

        int maxArea = 0;
        int left=0;
        int right = height.length-1;
         while (left<right){
                maxArea = Math.max(maxArea,(right-left)*Math.min(height[right],height[left]));
                if(height[left]<height[right]){
                    left++;
                }else {
                    right--;
                }
        }
        return maxArea;
    }

    public int maxArea1(int[] height){
        int size = height.length;
        int left=0, right=size-1;
        int ans = 0;
        while(left < right){
            ans = Math.max(ans, (right-left)*Math.min(height[left], height[right]));
            if(height[left] > height[right]) --right;
            else ++left;
        }
        return ans;
    }
}
