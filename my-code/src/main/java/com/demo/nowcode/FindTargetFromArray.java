package com.demo.nowcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
 * 你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
 * 假设给出的数组中只存在唯一解
 * 例如：
 * 给出的数组为 {20, 70, 110, 150},目标值为90
 * 输出 index1=1, index2=2
 * 示例1 [3,2,4],6
 */
public class FindTargetFromArray {

    public static void main(String[] args) {
//        int[] numbers = {3, 2, 4};
//        int target = 6;
//        printArray(twoSum(numbers, target));
    }

    public static void printArray(int[] a) {
        for (int i : a) {
            System.out.print(i);
            System.out.print(",");
        }
        System.out.println();
    }

    public static int[] twoSum(int[] numbers, int target) {
        List<List<Integer>> result = new ArrayList<>();

        int index1 = 0;
        int index2 = 0;
        boolean find = false;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 1; j < numbers.length; j++) {
                if ((target == numbers[i] + numbers[j]) && i != j) {
                    index1 = i;
                    index2 = j;
                    find = true;
                    break;
                }
            }
            if (find) {
                break;
            }
        }

        int[] sumTwo = new int[2];
        sumTwo[0] = index1 + 1;
        sumTwo[1] = index2 + 1;
        return sumTwo;
    }
    public static List<List<Integer>> threeSum(int[] numbers, int target) {
        
        List<List<Integer>> result = new ArrayList<>();
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length; j++) {
                for (int k = 2; k< length; k++){
                    if ((target == numbers[i] + numbers[j]+ numbers[k] ) && i != j && j!=k) {
                        List<Integer> three = new ArrayList<>();
                        three.add(i+1);
                        three.add(j+1);
                        three.add(k+1);
                        result.add(three);
                    }
                }
            }
        }
        return result;
    }
}
