package com.demo.leetcode;


import lombok.val;
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
         System.out.print(node.val +" ");
         node = node.next;
     }
      System.out.println();
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
      ListNode nd_6 = new ListNode(6);
//      nd_5.next = nd_6;
//      nd_6.next = nd_4;
      printListNode(head);
      printListNode(indexMiddle(head));

  }


  //合并两个有序链表

  //删除链表倒数第n个节点
    //求链表的中间节点


    public ListNode indexMiddle(ListNode head){
        ListNode slowPoint = head;
        ListNode fastPoint = head;
        boolean isOdd = false; //是否是奇数个节点

        //slowPoint 步长为1；fastPoint 步长为2；
        while (fastPoint!=null){

            fastPoint =fastPoint.next;
            if(fastPoint==null){
                //reach the link end
                 isOdd = true;
                 break;
            }
            fastPoint =fastPoint.next;
            if(fastPoint==null){
                //reach the link end
                isOdd = false;
                break;
            }
            slowPoint =slowPoint.next;
        }
        if(isOdd){
            return new ListNode(slowPoint.val);
        }else{
            ListNode node1  = new ListNode(slowPoint.val);
            ListNode node2  = new ListNode(slowPoint.next.val);
            node1.next = node2;
            return node1;
        }

    }

    /**
     * 链表中环检测
     * @param head
     * @return
     */
  public boolean circleTest(ListNode head){
      ListNode p1 = head;
      ListNode p2 = head;

      //p1 步长为1；p2步长为2；
      while (p1!=null && p2!=null){
          p2 =p2.next;
          if(p2==null){
              //reach the link end
            return false;
          }
          p2 =p2.next;
          p1 =p1.next;
          if(p1 == p2){
              return true;
          }
      }
      return false;
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
