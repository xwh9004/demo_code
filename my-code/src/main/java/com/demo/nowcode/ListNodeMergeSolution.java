package com.demo.nowcode;

public class ListNodeMergeSolution {

    static class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val){
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(5);
        ListNode l4 = new ListNode(7);
        l1.next = l2;
        l2.next =l3;
        l3.next =l4;

        ListNode k1 = new ListNode(2);
        ListNode k2 = new ListNode(6);
        ListNode k3 = new ListNode(9);
        ListNode k4 = new ListNode(10);
        k1.next = k2;
        k2.next =k3;
        k3.next =k4;
        ListNode r1 = mergeTwoLists(l1, k1);
        System.out.println(r1);

    }


    public static ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode l3 = new ListNode(0);
        ListNode p3 = l3;
        if(p1.val < p2.val){
            l3.val = p1.val;
            p1 = p1.next;
        }else{
            l3.val = p2.val;
            p2 = p2.next;
        }

        while (p1!=null&&p2!=null){
            ListNode node = new ListNode(0);
            if(p1.val < p2.val){
                node.val = p1.val;
                p1 = p1.next;
            }else{
                node.val = p2.val;
                p2 = p2.next;
            }
            p3.next = node;
            p3 =p3.next;
        }

        if(p1!=null){
            p3.next = p1;
        }
        if(p2!=null){
            p3.next = p2;
        }

        return l3;
    }
}
