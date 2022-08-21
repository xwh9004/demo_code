package com.demo.leetcode;

import org.junit.Test;

import java.util.HashMap;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 */
public class SingleNumberSolution {

    @Test
    public void test(){
         int[] nums ={-1,-1,-2};
        System.out.println(singleNumber(nums));
    }

    public int singleNumber(int[] nums) {
        HashMap<Integer,Integer> countMap = new HashMap<>();
        for (int i:nums){
            countMap.put(i,countMap.getOrDefault(i,0)+1);
        }
        for (Integer k:countMap.keySet()){
            if(countMap.get(k).equals(1)){
                return k;
            }
        }
        return 0;

    }


}
