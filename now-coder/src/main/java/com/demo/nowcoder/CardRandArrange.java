package com.demo.nowcoder;

import java.util.Scanner;

/**
 * 8785,86,87
 * 87,8785,86
 *
 */
public class CardRandArrange {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input =  scanner.nextLine();
            String[] values=input.split(",");
            System.out.println(maxConcat(values));
        }
//        System.out.println(compareStringNum("4342","43"));
    }

    public static String maxConcat(String[] cardNumbs ){
        if(null==cardNumbs){
            return "";
        }

      boolean hasSame =false;
      for (int i=0;i<cardNumbs.length;i++){
          for (int j=i+1;j<cardNumbs.length;j++){
              if(compareStringNum(cardNumbs[i],cardNumbs[j],cardNumbs)<0){
                  exchange(cardNumbs,i,j);
              }
          }
      }
      StringBuilder sb = new StringBuilder();
      for (int i=0;i<cardNumbs.length;i++){
          sb.append(cardNumbs[i]);
      }
      return sb.toString();

    }

    public static void exchange(String[] cardNumbs,int i,int j){

        String tmp =cardNumbs[i];
        cardNumbs[i] = cardNumbs[j];
        cardNumbs[j] =tmp;
    }

    public static int compareStringNum(String str1,String str2,String[] cardNumbs){
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
        StringBuffer sb = new StringBuffer();
        int res = 0;
        int index = 0;
        for (int i=0;i<len;i++){
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if(c1!=c2){
                res = c1-c2;
                index =i;
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
        }else{
            //长的大



        }

        return res;

    }


}
