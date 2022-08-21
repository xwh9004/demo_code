package com.demo.leetcode;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * @author created by Jesse Hsu at 14:25 on 2021/3/15
 * @version V0.1
 * @classNmae MinStack
 */
public class MinStack {

    private Node head;

    static class Node{
        private Node next;
        private int value;

        public Node(int x){
            this.value =x;
        }
    }


    public MinStack() {

    }

    public void push(int x) {
        if(head ==null){
            head = new Node(x);
            insertFirst(x);
        }else{
           int min = head.value;
           insertFirst(x);
           insertFirst(Math.min(min,x));
        }

    }

    private void insertFirst(int x){
        Node node = new Node(x);
        node.next =head;
        head = node;
    }
    public void pop() {
        head =head.next.next;
    }

    public int top() {
      return head.next.value;
    }

    public int getMin() {
        return head.value;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());   //--> 返回 -3.
        minStack.pop();
        minStack.top();     // --> 返回 0.
        System.out.println( minStack.getMin());  //--> 返回 -2.

    }
}
