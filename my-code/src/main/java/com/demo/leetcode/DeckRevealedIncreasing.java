package com.demo.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class DeckRevealedIncreasing {

    public static void main(String[] args) {
//        int[] arr ={17,13,11,2,3,5,7};
        int[] arr = {1,2,3,4,5,6,7,8,9};
        int [] result=deckRevealedIncreasing(arr);
        printArray(result);
    }

    public static void printArray(int[] arr){
        for (int a:arr){
            System.out.print(a+" ");
        }
        System.out.println();
    }
    public static int[] deckRevealedIncreasing(int[] deck) {
        int N = deck.length;
        Deque<Integer> index = new LinkedList();
        for (int i = 0; i < N; ++i)
            index.add(i);

        int[] ans = new int[N];
        Arrays.sort(deck);
        for (int card: deck) {
            ans[index.pollFirst()] = card;
            if (!index.isEmpty())
                index.add(index.pollFirst());
        }

        return ans;
    }
}
