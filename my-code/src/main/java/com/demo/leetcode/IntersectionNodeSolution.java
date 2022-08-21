package com.demo.leetcode;

/**
 *
 */
public class IntersectionNodeSolution {

    public static void main(String[] args) {
        IntersectionNodeSolution solution = new IntersectionNodeSolution();
        ListNode list1 = new ListNode(4);
        ListNode node1_1 = new ListNode(1);
        ListNode node8 = new ListNode(8);
        list1.next = node1_1;
        node1_1.next = node8;
        ListNode node4 = new ListNode(4);
        node8.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;

        ListNode list2 = new ListNode(5);

        ListNode node0 = new ListNode(0);
        list2.next = node0;
        ListNode node2_1 = new ListNode(1);
        node0.next = node2_1;
        list2.next = node8;
        ListNode node = solution.getIntersectionNode(list1, list2);
        System.out.println(node.val);
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode pA = headA;
        ListNode pB = headB;
        while (pA!=null && pB!=null){
            pA = pA.next;
            pB = pB.next;
        }

        if(pA==null){
            //链表headA较短
            pA = headB;
            while (pB!=null){
                pA = pA.next;
                pB = pB.next;
            }
            pB = headA;
            while (pA!=null && pB!=null && pA!=pB){
                pA = pA.next;
                pB = pB.next;
            }
            if(pA==null || pB==null){
                return null;
            }else {
                return pA;
            }
        }
        if(pB==null){
            //链表headA较短
            pB = headA;
            while (pA!=null){
                pA = pA.next;
                pB = pB.next;
            }
            pA = headB;
            while (pA!=null && pB!=null && pA!=pB){
                pA = pA.next;
                pB = pB.next;
            }
            if(pA==null || pB==null){
                return null;
            }else {
                return pA;
            }
        }
        return null;

    }




}
