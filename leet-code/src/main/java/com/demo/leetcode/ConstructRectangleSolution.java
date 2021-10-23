package com.demo.leetcode;

import org.junit.Test;

public class ConstructRectangleSolution {

    @Test
    public void test() {
       int[] rect = constructRectangle1(19);
        System.out.println(rect[0]);
        System.out.println(rect[1]);
    }

    public int[] constructRectangle(int area) {
        int width = (int) Math.sqrt(area);

        int length = width;
        int cal = length*width;
        while (cal !=area){
            if(cal>area){
                width--;
            }else {
                length++;
            }
            cal = length*width;
        }

        return new int[]{length,width};
    }
    public int[] constructRectangle1(int area) {
        int width = (int) Math.sqrt(area);
        while (area%width !=0){
            width--;
        }

        return new int[]{area/width,width};
    }

}
