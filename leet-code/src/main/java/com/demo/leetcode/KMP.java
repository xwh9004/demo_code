package com.demo.leetcode;

public class KMP {


    public static void main(String[] args) {

        char[] pat = "ababacd".toCharArray();
        int[] next = getNexts(pat,pat.length);
        printArray(next);
    }

    private static void printArray(int[] arr){
        for (int i:arr ) {
            System.out.print(i+" ");
        }
    }

    // a, b分别是主串和模式串；n, m分别是主串和模式串的长度。
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && a[i] != b[j]) { // 一直找到a[i]和b[j]
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            if (j == m) { // 找到匹配模式串的了
                return i - m + 1;
            }
        }
        return -1;
    }

    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            while (k != -1 && b[k + 1] != b[i]) {
                //next[k],是b[0,i]的次长子串
                k = next[k];
            }
            //如果次长子串的下一个字符==b[i],k+1。
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }
}
