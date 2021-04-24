package com.demo.mycode;

/**
 * 小顶堆 数组实现
 */
public class MyMaxHeap<T extends Comparable> {

    private Object[] values;

    private final int defaultSize =16;

    private int size;

    public MyMaxHeap(){
        values = new Object[defaultSize];
    }
    public MyMaxHeap(int capacity){
        values = new Object[capacity];
    }

    public void insert(T value){
        if(size == 0){
            values[++size] =value;
            return;
        }
        values[++size] =value;
        int k =size;
        //查找出
        swim(k);
    }

    private void swim(int k) {
        while ( k >1 && less(k, k /2)){
            exchange(k, k /2);
            k = k /2;
        }
    }

    public T deleteMin(){
        Object min = values[1];
        exchange(1,size);
        values[size--] = null;
        sink(1);
        return (T)min;
    }

    private void sink(int k) {
        while ( 2*k <= size){
            int j =2*k;
            if(j<size &&!less(j,j+1)){
                j++;
            }
            if(less(k,j))break;
            exchange(k, j);
            k = j;
        }
    }

    /**
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i,int j){
        Comparable iObi = (Comparable) values[i];
        Comparable iObj = (Comparable) values[j];
        return iObi.compareTo(iObj)<0;
    }

    private void exchange(int i,int j){
        Object tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }


    public static void main(String[] args) {
        MyMaxHeap<Integer> maxHeap = new MyMaxHeap<>();
        maxHeap.insert(6);
        maxHeap.insert(7);
        maxHeap.insert(8);
        maxHeap.insert(9);
        maxHeap.insert(10);
        maxHeap.insert(5);
        System.out.println();
        System.out.println(maxHeap.deleteMin());
        System.out.println(maxHeap.deleteMin());
        System.out.println(maxHeap.deleteMin());
        System.out.println(maxHeap.deleteMin());

        System.out.println();
    }
}
