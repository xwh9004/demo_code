package com.demo.nowcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ArrayMaxLength {

    public static void main(String[] args) {
        int[] arr ={6,2,1,5,7,1,2,1,4,3};
        int[] arr1 ={6,2,1,5,7};
        System.out.println(maxLength(arr));
        System.out.println(maxLength1(arr));
    }

    public static int maxLength (int[] arr) {
        if(arr.length<2){
            return arr.length;
        }
        int max = 0;
        int start = 0;
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0;i<arr.length;i++){
            if(map.containsKey(arr[i])){
               start =Math.max(start,map.get(arr[i])+1);
            }
            map.put(arr[i],i);
            max = Math.max(max,i-start+1);
        }
        return max;
    }

    public static int maxLength1 (int[] arr) {

        if(arr.length<2){
            return arr.length;
        }
        int max = 0;
        int start = 0;
        int end = 0;
        Set<Integer> set = new HashSet<>();
        while (end<arr.length){
            if(set.contains(arr[end])){
                set.remove(arr[start]);
                start++;
            }else{
                set.add(arr[end]);
                end++;
            }
            max =  Math.max(max,set.size());
        }
        return max;
    }
}
