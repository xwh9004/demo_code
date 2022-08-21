package com.demo.leetcode;

import java.util.Stack;

/**
 * 面试题 03.04. 化栈为队
 * 实现一个MyQueue类，该类用两个栈来实现一个队列。
 */
public class MyQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if (stack1.isEmpty()) {
            //move stack2 element to stack1
            while (!stack2.isEmpty()) {
                int v = stack2.pop();
                stack1.push(v);
            }
        }
        stack1.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        //move stack1 element to stack2
        while (!stack1.isEmpty()) {
            int v = stack1.pop();
            stack2.push(v);
        }
        int element = stack2.pop();
        return element;
    }

    /**
     * Get the front element.
     */
    public int peek() {
        while (!stack1.isEmpty()) {
            int v = stack1.pop();
            stack2.push(v);
        }
        return  stack2.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {

        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();

        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        System.out.println(queue.peek());  // 返回 1
        System.out.println(queue.peek());   // 返回 2
        queue.push(5);
        queue.push(6);
        System.out.println(queue.pop());   // 返回 3
        System.out.println(queue.pop());   // 返回 4
        System.out.println(queue.pop());   // 返回 5
        System.out.println(queue.pop());   // 返回 6
        System.out.println(queue.empty()); // 返回 false

    }
}
