package com.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesisSolution {

    public static void main(String[] args) {
        GenerateParenthesisSolution solution = new GenerateParenthesisSolution();
        System.out.println(solution.generateAll(2));
    }


    public List<String> generateAll(int n){
        List<String> combinations = new ArrayList<String>();
        char[] current = new char[2*n];
        generateAll(current,0,combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
            result.add(new String(current));

        } else {
            current[pos] = '0';
            generateAll(current, pos + 1, result);
            current[pos] = '1';
            generateAll(current, pos + 1, result);
        }
    }
    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

}
