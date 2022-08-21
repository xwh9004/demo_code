package com.demo.leetcode;

public class NodeListGenerator {

    static  ListNode generatorNodeList(int[] values){
        if(values.length == 0){
            return null;
        }
        ListNode head = null;
        ListNode np = null;
        for (int value:values) {
            ListNode node = new ListNode(value);
            if(head == null){
                head = node;
                np = head;
            }else {
                np.next = node;
                np=np.next;
            }
        }
        return head;

    }
}
