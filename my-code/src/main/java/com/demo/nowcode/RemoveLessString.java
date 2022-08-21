package com.demo.nowcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 实现删除字符串中出现次数最少的字符，若多个字符出现次数一样，则都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 * 注意每个输入文件有多组输入，即多个字符串用回车隔开
 */
public class RemoveLessString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input =  scanner.next();
            System.out.println(removeLessString(input));
        }

    }

    private static String removeLessString(String str) {
        Map<Character,Integer> map = new HashMap();

        for(int i=0;i<str.length();i++){
           char k = str.charAt(i);
           if(map.containsKey(k)){
               int count = map.get(k);
               map.put(k,count+1) ;
           }else{
                map.put(k,1) ;
            }
        }

        Set<Character> kSet = map.keySet();
        int min = 1;
        for (char k: kSet){
            min = Math.min(min,map.get(k));
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0;i<str.length();i++){
           char k = str.charAt(i);
           if(map.get(k)!=min){
               sb.append(k);
           }
       }
        return sb.toString();

    }
}
