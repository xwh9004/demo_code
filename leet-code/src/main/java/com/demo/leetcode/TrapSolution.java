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
//        int[] array={0,1,0,2,1,0,1,3,2,1,2,1};
        int[] array={0,1,2,3,4,3,2,1};
//        int[] array={9,6,8,8,5,6,3};  //
//        int[] array={5,2,1,2,1,5};
//        int[] array={2,1,0,2};
        System.out.println(solution.trap2(array));

        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(2);
        stack.push(3);
        System.out.println(solution.calculateVolume(stack));

    }

    public int trap2(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int water = 0;
        //特殊情况
        if(height.length <3){
            return 0;
        }
        for(int i = 0; i < height.length; i++){
            while(!stack.isEmpty() && height[i] > height[stack.peek()]){
                //栈顶元素
                int popnum = stack.pop();
                //相同元素的情况例1，1
                while(!stack.isEmpty()&&height[popnum] == height[stack.peek()]){
                    stack.pop();
                }
                //计算该层的水的单位
                if(!stack.isEmpty()){
                    int temp = height[stack.peek()];//栈顶元素值
                    //高
                    int hig = Math.min(temp-height[popnum],height[i]-height[popnum]);
                    //宽
                    int wid = i-stack.peek()-1;
                    water +=hig * wid;
                }

            }
            //这里入栈的是索引
            stack.push(i);
        }
        return water;
    }


    public int trap(int[] array) {
        if(array.length<=2){
            return 0;
        }
        int volume = 0;
        Stack<Integer> stack = new Stack<>();
        int leftMax =0;
        for (int i=0;i<array.length;i++){
            int value = array[i];
            if(stack.isEmpty()){
                leftMax = value;
                stack.push(value);
                continue;
            }

            int top = stack.pop();

            if(top<=value && stack.isEmpty()){
                    leftMax = value;
                    stack.push(value);
            }else {
                stack.push(top);
                stack.push(value);
                if(value>=leftMax){
                    volume += calculateVolume(stack);
                    leftMax = value;
                    stack.push(value);

                }
            }
        }
        while (!stack.isEmpty()){
            volume += calculateVolume(stack);
        }
        return volume;
    }


    private int calculateVolume(Stack<Integer> stack) {
        int rightMax =stack.pop();
        while (!stack.isEmpty() && stack.peek() > rightMax){
            rightMax = stack.pop();
        }
        int taken = 0;
        int left = 0;
        int count = 0;
        while (!stack.isEmpty() && (left = stack.peek()) <rightMax){
            left=stack.pop();
            count++;
            taken+=left;
        }
       int volume = Math.min(left,rightMax)*count -taken;
        return volume;
    }


}
