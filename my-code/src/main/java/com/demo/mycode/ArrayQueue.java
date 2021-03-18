package com.demo.mycode;

/**
 * <p><b>Description:</b>
 * 基于数组的循环队列
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 15:05 on 2021/3/16
 * @version V0.1
 * @classNmae ArrayQueue
 */
public class ArrayQueue {

    private int[] items;

    private int head = 0;

    private int tail = 0;

    private int capacity = 0;

    public ArrayQueue(int capacity){
        items = new int[capacity];
        this.capacity = capacity;
    }

    public boolean enqueue(int item){
        if(isFull()){
            return false;
        }
        items[tail] = item;
        tail = (tail+1)%capacity;
        return true;

    }

    private boolean isFull(){
        if((tail+1)%capacity==head){
            return true;
        }
        return false;
    }
    private boolean isEmpty(){
        if(tail==head){
            return true;
        }
        return false;
    }
    public int dequeue(){
        if(isEmpty()){
            return -1;
        }
        int item = items[head];
        head=(head+1)%capacity;
        return item;
    }


    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(8);
        for(int i=1;i<10;i++){
            System.out.println( queue.enqueue(i));
        }
        for(int i=1;i<5;i++){
            System.out.println(queue.dequeue());
        }
        for(int i=1;i<10;i++){
            System.out.println( queue.enqueue(i));
        }
    }

}
