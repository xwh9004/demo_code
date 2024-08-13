package com.demo.leetcode;

/**
 * @author: xwh90
 * @date: 2024/8/7 22:37
 * @description:
 */
public class PartitionSolution {

    public static void main(String[] args) {
        ListNode head = ListNode.createNodeList(new int[]{1});
        final ListNode node = partition(head, 0);
        System.out.println(ListNode.values(node));
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode h1 = null; //小于x的头的头指针
        ListNode h2 = null; //大于等于x的头指针
        ListNode p1 = null; //小于x的当前指针
        ListNode p2 = null; //大于等于x的当前指针
        while (head != null) {
            if (head.val < x) {
                if (h1 == null) {
                    p1 = h1 = head;
                } else {
                    p1.next = head;
                    p1 = p1.next;
                }
            } else {
                if (h2 == null) {
                    p2 = h2 = head;
                } else {
                    p2.next = head;
                    p2 = p2.next;
                }
            }
            head = head.next;
        }
        if (p2 != null) p2.next = null;
        if (p1 != null) p1.next = h2;
        return h1 != null ? h1 : h2;
    }
}
