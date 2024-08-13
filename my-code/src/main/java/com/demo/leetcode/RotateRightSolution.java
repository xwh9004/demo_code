package com.demo.leetcode;

/**
 * @author: xwh90
 * @date: 2024/8/10 19:41
 * @description: 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 */
public class RotateRightSolution {

    public static void main(String[] args) {
        RotateRightSolution solution = new RotateRightSolution();
//        int[] values = new int[]{1, 2, 3, 4, 5};
//        int k = 2;
        int[] values = new int[]{0,1,2};
        int k = 4;
        ListNode head = ListNode.createNodeList(values);

        final ListNode node = solution.rotateRight(head, k);
        System.out.println(ListNode.values(node));
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return null;
        }
        int n = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            n++;
        }
        int k0 = k % n;
        if(k0 ==0){
            return head;
        }
        int moves = n - k0;
        tail.next =head;
        while (moves-- > 0) {
            tail = tail.next;
        }
        ListNode newHead = tail.next;
        tail.next =null;
        return newHead;
    }
}
