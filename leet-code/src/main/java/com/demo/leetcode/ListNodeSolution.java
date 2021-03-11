package com.demo.leetcode;


import org.junit.Test;

public class ListNodeSolution {

  static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
  }

  public void printListNode(ListNode head){
     ListNode node = head;
     while (node!=null){
         System.out.print(node.val +"——>");
         node = node.next;
     }
      System.out.println("null");
  }

  @Test
  public void test(){
      ListNode head = new ListNode(1);
      ListNode nd_2 = new ListNode(2);
      head.next = nd_2;
      ListNode nd_3 = new ListNode(3);
      nd_2.next = nd_3;
      ListNode nd_4 = new ListNode(4);
      nd_3.next = nd_4;
      ListNode nd_5 = new ListNode(5);
      nd_4.next = nd_5;
      printListNode(head);
      head = reverseList1(head);
      printListNode(head);

  }
    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点
     */
    public ListNode reverseList(ListNode head) {
        ListNode n_head = null;
        ListNode node = head;
        while (node!=null){
            ListNode n_node = new ListNode(node.val);
            if(n_head ==null){
                n_head = n_node;
            }else{
                n_node.next = n_head;
                n_head = n_node;
            }
            node = node.next;
        }
      return n_head;
    }
    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点
     */
    public ListNode reverseList1(ListNode head) {
        ListNode prev =null;
        ListNode next =null;
        ListNode current =head;
        while (current!=null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;

        }
        head = prev;
        return head;
    }
}
