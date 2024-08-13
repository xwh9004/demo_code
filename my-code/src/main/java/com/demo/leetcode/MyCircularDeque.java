package com.demo.leetcode;

/**
 * @author: xwh90
 * @date: 2024/8/13 22:50
 * @description:
 */
public class MyCircularDeque {
    private int[] data;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public MyCircularDeque(int k) {
        this.capacity = k;
        data = new int[k];
        front = 0;
        rear = 0;
        size = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        if (size != 0) {
            front = front - 1;
            if (front < 0) {
                front = capacity - 1;
            }
        }
        data[front] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) return false;
        if (size != 0) {
            rear = rear + 1;
            if (rear >= capacity) {
                rear = 0;
            }
        }
        data[rear] = value;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) return false;
        size--;
        if (size != 0) {
            front++;
        }
        if (front >= capacity) {
            front = 0;
        }
        return true;

    }

    public boolean deleteLast() {
        if (isEmpty()) return false;
        size--;
        if (size != 0) {
            rear--;
        }
        if (rear < 0) {
            rear = capacity - 1;
        }
        return true;
    }

    public int getFront() {
        if (size == 0) {
            return -1;
        }
        return data[front];
    }

    public int getRear() {
        if (size == 0) {
            return -1;
        }
        return data[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }


    public static void main(String[] args) {
        MyCircularDeque deque = new MyCircularDeque(2);
        //[[2],[7],[],[],[5],[0],[],[],[],[],[],[0]]
        System.out.println(deque.insertFront(7));
        System.out.println(deque.deleteFront());
        System.out.println(deque.getFront());
        System.out.println(deque.insertLast(5));
        System.out.println(deque.insertFront(0));
        System.out.println(deque.getFront());
        System.out.println(deque.getRear());
        System.out.println(deque.getFront());
        System.out.println(deque.getFront());
        System.out.println(deque.getRear());
        System.out.println(deque.insertLast(0));
        // "MyCircularDeque","insertFront","deleteLast","getFront","insertLast","insertFront",
        // "getFront","getRear","getFront","getFront","getRear","insertLast"
    }
}
