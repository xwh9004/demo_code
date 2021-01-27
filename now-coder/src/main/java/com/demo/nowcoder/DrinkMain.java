package com.demo.nowcoder;

import java.util.Scanner;

/**
 * 3个瓶盖 兑换一个饮料，求n的瓶盖最多可以兑换多少瓶饮料
 */
public class DrinkMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            int value =Integer.valueOf(input);
            if(value==0){
                continue;
            }
            System.out.println(many( Integer.valueOf(input)));
        }
    }
    public static int many(int n){
        int[] a = new int[101];

        a[0] = 0;
        a[1] = 0;
        a[2] = 1;
        a[3] = 1;
        for(int i=4;i<101;i++){
            a[i] = i/3 +a[i/3+i%3];
        }
        return a[n];
    }
}


