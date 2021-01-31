package com.demo.nowcoder;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input =  scanner.nextLine();
            String[] values=input.split(",");
            System.out.println(maxConcat(values));
        }
        System.out.println(compareStringNum("123","2131"));
    }

    public static String maxConcat(String[] cardNums ){
        if(null==cardNums){
            return "";
        }

      for (int i=0;i<cardNums.length;i++){
          for (int j=i+1;j<cardNums.length;j++){
              if(compareStringNum(cardNums[i],cardNums[j])<0){
                  exchange(cardNums,i,j);
              }
          }
      }
      StringBuilder sb = new StringBuilder();
      for (int i=0;i<cardNums.length;i++){
          sb.append(cardNums[i]);
      }
      return sb.toString();

    }

    public static void exchange(String[] cardNums,int i,int j){

        String tmp =cardNums[i];
        cardNums[i] = cardNums[j];
        cardNums[j] =tmp;
    }

    public static int compareStringNum(String str1,String str2){
        if(null == str1&&str2==null){
            return 0;
        }
        if(str1 == null){
            return 1;
        }
        if(str2 == null){
            return -1;
        }
        int len1 = str1.length();
        int len2 =str2.length();
        int len = Math.min(len1,len2);

        if(len1 ==0&&len2 ==0){
            return 0;
        }
        if(len1 ==0){
            return -1;
        }
        if(len2 ==0){
            return 1;
        }

        int res = 0;
        for (int i=0;i<len;i++){
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if(c1!=c2){
                res = c1-c2;
                break;
            }
        }
        if(res == 0){
            if(len==len1){
                //str1 短
               int c1 = str1.charAt(len-1);
               for (int i =len;len<len2;i++){
                   char c2 = str2.charAt(i);
                   if(c1!=c2){
                       res = c1-c2;
                       break;
                   }
               }
            }else{
                //str2 短
                int c2 = str2.charAt(len-1);
                for (int i =len;len<len1;i++){
                    char c1 = str1.charAt(i);
                    if(c1!=c2){
                        res = c1-c2;
                        break;
                    }
                }
            }
        }

        return res;

    }


}
