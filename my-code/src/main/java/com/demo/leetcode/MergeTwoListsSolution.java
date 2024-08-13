package com.demo.leetcode;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeTwoListsSolution {


    public static void main(String[] args) {
        MergeTwoListsSolution solution = new MergeTwoListsSolution();
        ListNode list1 = new ListNode(1);
        ListNode node1_2 = new ListNode(2);
        ListNode node1_4 = new ListNode(4);
        list1.next = node1_2;
        node1_2.next = node1_4;

        ListNode list2 = new ListNode(1);
        ListNode node2_3 = new ListNode(3);
        ListNode node2_4 = new ListNode(4);
        list2.next = node2_3;
        node2_3.next = node2_4;
        solution.mergeTwoLists(null,list2);


    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
           if(l1==null){
               return l2;
           }
           if(l2==null){
            return l1;
           }
           ListNode p1 = l1;
           ListNode p2 = l2;
           ListNode result = null;
           ListNode rp = null;
           while (p1!=null && p2!=null){
               if(p1.val<=p2.val){
                   if(result==null){
                       result =p1;
                       rp = result;
                   }else {
                       rp.next = p1;
                       rp = rp.next;
                   }
                   p1 =p1.next;
               }else {
                   if(result==null){
                       result =p2;
                       rp = result;
                   }else {
                       rp.next = p2;
                       rp = rp.next;
                   }
                   p2 = p2.next;
               }
           }

           if(p1!=null){
              rp.next =p1;
           }
           if(p2!=null){
               rp.next =p2;
            }
           return result;
    }
}
