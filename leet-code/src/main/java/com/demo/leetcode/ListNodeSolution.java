package com.demo.leetcode;


import lombok.val;
import org.junit.Before;
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

  public  ListNode head = null;
  @Before
  public void headNode(){
       head = new ListNode(1);
      ListNode nd_2 = new ListNode(2);
      head.next = nd_2;
      ListNode nd_3 = new ListNode(3);
      nd_2.next = nd_3;
      ListNode nd_4 = new ListNode(4);
      nd_3.next = nd_4;
      ListNode nd_5 = new ListNode(5);
      nd_4.next = nd_5;
      ListNode nd_6 = new ListNode(6);
      nd_5.next = nd_6;
      nd_6.next = nd_2;

//      printListNode(indexMiddle(head));
//      System.out.println(taiIndexOf(head,1).val);
//      System.out.println(taiIndexOf(head,2).val);
//      System.out.println(taiIndexOf(head,3).val);
//      System.out.println(taiIndexOf(head,4).val);
//      System.out.println(taiIndexOf(head,5).val);
//      System.out.println(taiIndexOf(head,6).val);
//      printListNode(removeIndexOf(head,2));
//      printListNode(removeIndexOf(head,2));
//      printListNode(removeIndexOf(head,1));
//      printListNode(removeIndexOf(head,1));
//      printListNode(removeIndexOf(head,1));
//      printListNode(removeIndexOf(head,1));
  }
  @Test
  public void testPrint(){
      if(!circleTest(head)){
          printListNode(head);
      }else{
          System.out.println("list is contains one circle");
      }

  }

    @Test
    public void testNodeCircle(){
        System.out.println(circleStartAt(head).val);
    }


  //删除倒数第n个节点
  public ListNode removeIndexOf(ListNode head,int n){
      if(n<1){
          throw new IllegalArgumentException("n must be greater than 1");
      }
      ListNode prevNode = head;
      ListNode current_p = head;
      ListNode nextNode = head;
      int goSteps = n-1;
      while (nextNode!=null && goSteps>0){
          nextNode = nextNode.next;
          goSteps--;
      }
      if(goSteps>0||nextNode==null){
          throw new IndexOutOfBoundsException("n can not be greater than link size");
      }
      //nextNode move forward step than currentNode;
      nextNode = nextNode.next;
      //nextNode and current_p move forward one step than prevNode
      if(nextNode ==null){
          head = head.next;
          return head;
      }
      nextNode = nextNode.next;
      current_p = current_p.next;
      //nextNode,current_p,prevNode move forward together
      while (nextNode!=null){
          nextNode = nextNode.next;
          current_p = current_p.next;
          prevNode = prevNode.next;
      }
      //
      prevNode.next = current_p.next;
      return head;
  }
    /**
     *  查找链表倒数第n个节点
     *  n=1时,指向的链表中最后一个
     */

    public ListNode taiIndexOf(ListNode head,int n){
      if(n<1){
          throw new IllegalArgumentException("n must be greater than 1");
      }

      ListNode bp = head;
      ListNode fp = head;
      int goSteps = n-1;
      while (fp!=null && goSteps>0){
          fp = fp.next;
          goSteps--;
      }
      if(goSteps>0||fp==null){
          throw new IndexOutOfBoundsException("n can not be greater than link size");
      }
      fp = fp.next;
      while (fp!=null){
          fp = fp.next;
          bp = bp.next;
      }
      return bp;
    }
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

    public ListNode circleStartAt(ListNode head){
        ListNode start = head;
        ListNode atNode =circleEncounterAt(head);
        if(atNode!=null){
            ListNode circleRunNode = atNode;
            while (start!=null && start!=circleRunNode){
                start=start.next;
                circleRunNode =circleRunNode.next;

            }
            return circleRunNode;
        }
        return null;
    }

    public ListNode circleEncounterAt(ListNode head){
        ListNode p1 = head;
        ListNode p2 = head;

        //p1 步长为1；p2步长为2；
        while (p1!=null && p2!=null){
            p2 =p2.next;
            if(p2==null){
                //reach the link end
                return null;
            }
            p2 =p2.next;
            p1 =p1.next;
            if(p1 == p2){
                return p1;
            }
        }
        return null;
    }

    /**
     * 链表中环检测
     * @param head
     * @return
     */
  public boolean circleTest(ListNode head){
      ListNode encounterAt = circleEncounterAt(head);
      return encounterAt!=null;
  }
    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点
     */
    public ListNode reverseList(ListNode head) {
        ListNode n_head = null;
        ListNode node = head;
        while (node!=null){
            ListNode n_node = new ListNode(node.val);
            if (n_head != null) {
                n_node.next = n_head;
            }
            n_head = n_node;
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
