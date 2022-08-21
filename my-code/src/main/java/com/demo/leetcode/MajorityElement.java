package com.demo.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 */
public class MajorityElement {


    public static void main(String[] args) {
        int[] nums ={1,2,3,2,2,2,5,4,2};
        MajorityElement solution = new MajorityElement();

        System.out.println(solution.majorityElement_mol(nums));
    }
    public int majorityElement(int[] nums){
        Arrays.sort(nums);
        return   nums[nums.length/2];
    }

    public int majorityElement_hash(int[] nums){
        HashMap<Integer,Integer> countHash = new HashMap<>();
        for (int i = 0;i<nums.length;i++){
            int key =nums[i];
            if(countHash.containsKey(key)){
                Integer count =  countHash.get(key);
                count =count+1;
                countHash.put(key,count);
            }else{
                countHash.put(key,1);
            }
        }
        Set<Integer> keys = countHash.keySet();
        for (Integer k:keys){
            if(countHash.get(k).intValue()>nums.length/2){
                return k;
            }
        }
        return  0;
    }

    /**
     * 摩尔投票
     * 推论一： 若记 众数 的票数为 +1+1 ，非众数 的票数为 -1−1 ，则一定有所有数字的 票数和 > 0>0 。
     *
     * 推论二： 若数组的前 aa 个数字的 票数和 = 0=0 ，则 数组剩余 (n-a)(n−a) 个数字的 票数和一定仍 >0>0 ，即后 (n-a)(n−a) 个数字的 众数仍为 xx
     *
     * @param nums
     * @return
     */
    public int majorityElement_mol(int[] nums){
        int votes =0;
        int x = 0;
        for (int i = 0;i<nums.length;i++){
            if(votes==0){
                x = nums[i];
            }
            if(nums[i] == x){
                votes++;
            }else {
                votes--;
            }
        }
        return x;
    }
}
