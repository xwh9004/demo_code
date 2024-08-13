package com.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * @param values 1,2,3
     * @return 1 -> 2 ->3
     */
    public static ListNode createNodeList(int[] values) {
        ListNode head = new ListNode(values[0]);
        ListNode tmp = head;
        for (int i = 1; i < values.length; i++) {
            ListNode node = new ListNode(values[i]);
            tmp.next = node;
            tmp = tmp.next;
        }
        return head;
    }

    public static List<Integer> values(ListNode head) {
        List<Integer> values = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            values.add(node.val);
            node = node.next;
        }
        return values;
    }
}
