package com.demo.jvm;

public class IncreaseJvmCode {
    public static void main(String[] args) {

    }

    public static void iincre(){
        int i=0;
        for (int j=0;j<50;j++) {
            i=i++;
            System.out.println(i);
        }
        System.out.println(i);
    }
    public static void increi(){
        int i=0;
        for (int j=0;j<50;j++) {
            i=i++;
            System.out.println(i);
        }
        System.out.println(i);
    }
    public static void increii(){
        int i=0;
        for (int j=0;j<50;j++) {
            i=i++ + ++i;
            System.out.println(i);
        }
        System.out.println(i);
    }
}
