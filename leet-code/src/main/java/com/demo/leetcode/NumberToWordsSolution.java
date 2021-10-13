package com.demo.leetcode;

/**
 * 将非负整数 num 转换为其对应的英文表示。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 123
 * 输出："One Hundred Twenty Three"
 * 示例 2：
 * <p>
 * 输入：num = 12345
 * 输出："Twelve Thousand Three Hundred Forty Five"
 * 示例 3：
 * <p>
 * 输入：num = 1234567
 * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * 示例 4：
 * <p>
 * 输入：num = 1234567891
 * 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-english-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberToWordsSolution {

    public static void main(String[] args) {
        NumberToWordsSolution solution = new NumberToWordsSolution();
        System.out.println(solution.numberToWords(1000000));
        System.out.println(solution.numberToWords(50868));
//        System.out.println(solution.numberToWords(101));
//        System.out.println(solution.numberToWords(1001));
//        System.out.println(solution.numberToWords(1));
//        System.out.println(solution.numberToWords(12));
//        System.out.println(solution.numberToWords(123));
//        System.out.println(solution.numberToWords(1234));
//        System.out.println(solution.numberToWords(12345));
//        System.out.println(solution.numberToWords(1234567));
//        System.out.println(solution.numberToWords(1234567891));
    }

    public String numberToWords(int num) {
        if(num==0){
            return "Zero";
        }
        String[] kioUnits = {"", "Thousand", "Million", "Billion"};
        if (num > Integer.MAX_VALUE) {
            return null;
        }
        int div;
        String result = "";
        int times = 0;
        do {
            div = num / 1000;
            int mod = num % 1000;
            String temp = transfer(mod).trim();
            if(!temp.equals("")){
                temp = temp + " " + kioUnits[times];
            }
            result =  temp+ " " + result;
            result=result.trim();
            times++;
            num = num / 1000;

        } while (div != 0);

        return result.trim();
    }


    private String transfer(int num) {
        if(num==0){
            return "";
        }
        int j = num % 10;
        int i = num / 10 % 10;
        int k = num / 100;
        String[] geWords = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] words = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] tenWords = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String result = "";
        if (k > 0) {
            result = geWords[k] + " Hundred";
        }
        if (i ==0) {
            return result + " " + geWords[j];
        }
        if (i == 1) {
            return result + " " + tenWords[j];
        }
        return result + " " + words[i] + " " + geWords[j];
    }
}
