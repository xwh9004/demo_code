package com.demo.leetcode;

import org.junit.Test;

/**
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * v             5000
 * x             10000
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * M 可以放在 v (5000) 和 x (10000) 的左边，来表示 4000 和 9000。

 */
public class Int2Roman {
    @Test
    public void test(){

        System.out.println(intToRoman(3999));

    }

    /**
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     * @param num
     * @return
     */
    public String intToRoman(int num) {
       String[] romanSymbs = {"I","V","X","L","C","D","M","v","x"};
       int k = num/1000;
       int h = num%1000/100;
       int t = num%100/10;
       int s =num%10;

       return digitProcess(k,romanSymbs[6],romanSymbs[7],romanSymbs[8])
               .concat(digitProcess(h,romanSymbs[4],romanSymbs[5],romanSymbs[6]))
               .concat(digitProcess(t,romanSymbs[2],romanSymbs[3],romanSymbs[4]))
               .concat(digitProcess(s,romanSymbs[0],romanSymbs[1],romanSymbs[2]));
    }


    /**
     * @param s
     * @param symb1  I
     * @param symb2  V
     * @param symb3  X
     * @return
     *
     */
    public String digitProcess(int s,String symb1,String symb2,String symb3){
        if(s==0){
            return "";
        }
        if(s<4){
            return symb1.concat(digitProcess(s-1,symb1,symb2,symb3));
        }if(s==4){
            return symb1.concat(symb2);
        }else if(s==5){
            return symb2;
        }else if(s<9){
            return symb2.concat(digitProcess(s-5,symb1,symb2,symb3));
        }else {
            return symb1.concat(symb3);
        }
    }
}
