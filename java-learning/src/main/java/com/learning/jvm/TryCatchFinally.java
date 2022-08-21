package com.learning.jvm;

public class TryCatchFinally {

    public static void main(String[] args) {
       int i = foo();
        int j = bar();
        System.out.println(i);
        System.out.println(j);
        System.out.println(foo1());
    }

    private static int foo() {
        int i =100;
        try {
            return i;
        }finally {
            ++i;
        }
    }
    private static int bar() {
        int i =100;
        try {
            return i;
        }finally {
            ++i;
            return i;
        }
    }

    private static String foo1() {
       String s ="hello";
        try {
            return s;
        }finally {
            s =null;
        }
    }
}
