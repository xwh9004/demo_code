package com.demo.leetcode;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ZigZagSolution {

    public static void main(String[] args) {
        String s ="ABCD";
        int numRows =3;
        System.out.println(convert(s,numRows));
    }

    public static String convert(String s, int numRows) {
          if(numRows==1){
              return s;
          }

          int len = s.length();
          //根据s的长度len以及 numRows计算出需要的二维数组的大小，即 s可以形成多少个Z型
          int zNum = numRows * 2  -1;
          int cols = (len/zNum+1)*numRows;
          char[][] metric = new char[numRows][cols];
          int row = 0;
          int col = 0;
          for (int i = 0; i<len;i++){
              char c = s.charAt(i);
              metric[row][col] = c;
              int[] site = getNextSite(row,col,numRows);
              row = site[0];
              col = site[1];
          }
        return printCharArray(metric);
    }

    public static int[] getNextSite(int r,int c,int rowNums){
         int[] sites= new int[2];
          if(c%(rowNums-1)==0){
              if(r==(rowNums -1)){
                  sites[0] = r-1;
                  sites[1] = c+1;
              }else{
                  sites[0] = r+1;
                  sites[1] = c;
              }
          }else {
              sites[0] = r-1;
              sites[1] = c+1;
          }
          return sites;
    }

    public static String printCharArray(char[][] metric){
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ;i<metric.length;i++){
            for (int j=0;j<metric[i].length;j++){
                char c = metric[i][j];
                   if(c!='\u0000'){
                       sb.append(c);
                   }
            }
        }
        return sb.toString();
    }
}
