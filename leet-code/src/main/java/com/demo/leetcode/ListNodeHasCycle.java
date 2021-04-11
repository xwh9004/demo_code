package com.demo.leetcode;

/**
 * 判断链表是否有环
 */
public class ListNodeHasCycle {

    public static void main(String[] args) {

        ListNodeHasCycle solution = new ListNodeHasCycle();
        ListNode list1 = new ListNode(4);
        ListNode node1_1 = new ListNode(1);
        ListNode node8 = new ListNode(8);
        list1.next = node1_1;
        node1_1.next = node8;
        ListNode node4 = new ListNode(4);
        node8.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node1_1;

        System.out.println(solution.hasCycle(list1));
    }

    public boolean hasCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1!=null && p1.next!=null){
            p1 = p1.next.next;
            p2 = p2.next;
            if(p1 == p2){
                return true;
            }
        }
         return false;
    }

}
