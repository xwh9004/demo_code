package com.demo.nowcode;

import java.util.Scanner;

public class HexToInt {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(hexToInt(input));
        }
    }
        public static int hexToInt(String hex){
        String hexStr = hex;
        if(hex.startsWith("0x")){
            hexStr = hexStr.substring(2);
        }
        int sum = 0;
        int len = hexStr.length();
        int times =1;
        for (int i =len-2;i>=0;i--){
            String s = String.valueOf(hexStr.charAt(i));
            int value  =charToInt(s);
            times*=16;
            sum += times*value ;
        }
        String last = String.valueOf(hexStr.charAt(len-1));
        return sum+(charToInt(last));
    }

    public static int charToInt(String a){
        int value = 0;
        if(a.equalsIgnoreCase("A")){
            value =10;
        }else if(a.equalsIgnoreCase("B")){
            value =11;
        }else if(a.equalsIgnoreCase("C")){
            value =12;
        }else if(a.equalsIgnoreCase("D")){
            value =13;
        }else if(a.equalsIgnoreCase("E")){
            value =14;
        }else if(a.equalsIgnoreCase("F")){
            value =15;
        }else{
            value =Integer.valueOf(a);
        }
        return value;
    }
}
