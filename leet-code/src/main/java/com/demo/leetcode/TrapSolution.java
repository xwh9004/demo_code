package com.demo.leetcode;

import java.util.Stack;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 13:47 on 2021/3/19
 * @version V0.1
 * @classNmae TrapSolution
 */
public class TrapSolution {

    public static void main(String[] args) {
        TrapSolution solution = new TrapSolution();
        int[] array={0,1,0,2,1,0,1,3,2,1,3,1};
        System.out.println(solution.trap(array));
    }

    public int trap(int[] array) {
        if(array.length<=2){
            return 0;
        }
        int volume = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<array.length;i++){
            int value = array[i];
            if(stack.isEmpty()){
                stack.push(value);
                continue;
            }
            int top = stack.peek();
            if(top>=value){
                stack.push(value);
            }else {
                //计算面积
                stack.push(value);
                volume += calculateVolume(stack);
            }
        }

        return volume;
    }

    private int calculateVolume(Stack<Integer> stack) {
        int right =stack.pop();
        int taken = 0;
        int left = 0;
        int count = 0;
        while ((left=stack.peek())<right){
            left=stack.pop();
            count++;
            taken+=left;
        }

        return right;
    }


}
