package com.demo.mycode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class MyBitMap {

    private int bitCount = 0;
    private int bitLength = 0;
    private int[] bitHolder;
    private int bitHolderSize = 0;

    public MyBitMap() {
        this(0);
    }

    public MyBitMap(int length) {
        bitHolderSize = bitLength / 32;
        bitHolder = new int[bitHolderSize + 1];
        this.bitLength = bitHolder.length;
    }

    public void setBit(int n) {
        int bitHolderIndex = n / 32;
        int bitIndex = n % 32;
        if (bitHolderIndex > bitHolderSize) {
            ensureCapacity(bitHolderIndex);
        }
        if(testBit(n)){
            return ;
        }
        int indexIntValue = bitHolder[bitHolderIndex];
        indexIntValue = indexIntValue | (1 << bitIndex);
        bitHolder[bitHolderIndex] = indexIntValue;
        bitCount++;
    }

    public void clearBit(int n) {
        int bitHolderIndex = n / 32;
        int bitIndex = n % 32;
        int indexIntValue = bitHolder[bitHolderIndex];
        if(!testBit(n)){
            return;
        }
        int optValue = ~(1 << bitIndex);
        indexIntValue = indexIntValue & optValue;
        bitHolder[bitHolderIndex] = indexIntValue;
        bitCount--;
    }

    private void ensureCapacity(int size) {
         int olderLength = bitHolder.length;
         int newLength = size<<1;
         int[] newBitHolder = new int[newLength];
         arrayCopies(bitHolder,newBitHolder);
         bitHolder = newBitHolder;
         bitLength = bitHolder.length;
    }

    private void arrayCopies(int[] older, int[] newer) {
        for (int i = 0;i<older.length;i++){
            newer[i] =older[i];
        }

    }

    public int getBitLength() {
        return bitLength;
    }

    public int getBitCount() {
        return bitCount;
    }

    public boolean testBit(int n) {
        int bitHolderIndex = n / 32;
        int bitIndex = n % 32;
        int indexIntValue = bitHolder[bitHolderIndex];
        int andRes = indexIntValue & 1 << bitIndex;
        if(andRes==-2147483648){
                return true;
        }
        return andRes > 0;
    }
}
