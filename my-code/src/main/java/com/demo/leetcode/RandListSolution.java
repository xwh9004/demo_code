package com.demo.leetcode;

public class RandListSolution {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Node newHead = null;
        Node new_nd = newHead;
        Node node = head;
        while (node!=null){
           Node newNode =new Node(node.val);
           if(newHead ==null){
               new_nd =newNode;
           }
           newNode.next = node.next;
           newNode.random = node.random;
           new_nd.next = newNode;
       }

        return newHead;
    }
}
