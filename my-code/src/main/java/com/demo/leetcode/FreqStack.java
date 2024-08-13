package com.demo.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: xwh90
 * @date: 2024/8/11 20:07
 * @description: 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 * <p>
 * 实现 FreqStack 类:
 * <p>
 * FreqStack() 构造一个空的堆栈。
 * void push(int val) 将一个整数 val 压入栈顶。
 * int pop() 删除并返回堆栈中出现频率最高的元素。
 * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
 */
public class FreqStack {
    int maxFreq = 0;
    Map<Integer, Integer> freqMap = new HashMap<>();
    Map<Integer, Stack<Integer>> stackMap = new HashMap<>();

    public FreqStack() {

    }

    public void push(int val) {
        freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
        final Integer freq = freqMap.get(val);
        if (stackMap.containsKey(freq)) {
            final Stack<Integer> stack = stackMap.get(freq);
            stack.push(val);
        } else {
            maxFreq = freq;
            Stack<Integer> stack = new Stack<>();
            stack.push(val);
            stackMap.put(freq, stack);
        }
    }

    public int pop() {
        final Stack<Integer> stack = stackMap.get(maxFreq);
        int value = stack.pop();
        freqMap.put(value, freqMap.get(value) - 1);
        if (stack.isEmpty()) {
            stackMap.remove(maxFreq);
            maxFreq--;
        }
        return value;
    }
}
