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

        //
        if(index<len-1){
            //不同的位置出现在非末位的位置
            int value1= Integer.valueOf(str1);
            int value2= Integer.valueOf(str2);
            if(value1<value2){
                res = -1;
            }else{
                res =1;
            }
        }
        //index位置出现在短字串的末尾
        if(len1>len){
            //str1长，str2短
            if(index==-1){
                //短的字串是长字串的公共部分
                String subStr = str1.substring(len);
                int value1= Integer.valueOf(subStr);
                int value2= Integer.valueOf(str2);
                if(value1<value2){
                    res = -1;
                }else{
                    res =1;
                }
            }else{
                //
                String head = str1.substring(0,len);
                if(isExist(head,cardNumbs)){
                    String subStr = str1.substring(len);
                    int value1= Integer.valueOf(subStr);
                    int value2= Integer.valueOf(str2);
                    if(value1<value2){
                        res = -1;
                    }else{
                        res =1;
                    }
                }else{
                    res = str1.charAt(index) -str2.charAt(index);
                }

            }
        }else{
            //str1短，str2长
            if(index==-1){
                //短的字串是长字串的公共部分
                String subStr = str2.substring(len-1);
                int value1= Integer.valueOf(str1);
                int value2= Integer.valueOf(subStr);
                if(value1<value2){
                    res = -1;
                }else{
                    res =1;
                }
            }else{
                String head = str2.substring(0,len-1);
                if(isExist(head,cardNumbs)){
                    String subStr = str2.substring(len);
                    int value1= Integer.valueOf(str1);
                    int value2= Integer.valueOf(subStr);
                    if(value1<value2){
                        res = -1;
                    }else{
                        res =1;
                    }
                }else{
                    res = str1.charAt(index) -str2.charAt(index);
                }
            }
        }

        return res;

    }

    public static boolean isExist(String str,String[] strs) {
        for (int i = 0; i < strs.length; i++) {
            if (str.equals(strs[i])) {
                return true;
            }
        }
        return false;
    }

}
