package com.demo.nowcoder;

import java.util.Queue;
import java.util.Stack;

public class StackToQueue {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }

    public int pop() {
        while (!stack1.isEmpty()){
            int value = stack1.pop();
            stack2.push(value);
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        StackToQueue queue = new StackToQueue();
        queue.push(3);
        queue.push(2);
        queue.push(1);
        queue.push(5);
        queue.push(6);

        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }


}
