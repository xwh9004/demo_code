package com.demo.nowcoder;

import java.util.Scanner;
import java.util.Stack;

public class Main1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input =  scanner.nextLine();
            System.out.println(cleanRepeate(input));
        }

    }

    private static int cleanRepeate(String str) {
        Stack<Character> stacks = new Stack<>();
        for (int i =0;i<str.length();i++) {
            if ((str.charAt(i) <= 'A' && str.charAt(i) >= 'a')
                    || (str.charAt(i) <= 'z' && str.charAt(i) >= 'a')) {
                if (stacks.isEmpty()) {
                    stacks.push(str.charAt(i));
                } else {
                    char c = stacks.pop();
                    if (c != str.charAt(i)) {
                        stacks.push(c);
                        stacks.push(str.charAt(i));
                    }
                }
            } else {
                return 0;
            }
        }
        return stacks.size();
    }
}
