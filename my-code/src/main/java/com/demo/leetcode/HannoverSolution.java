package com.demo.leetcode;

/**
 * <p><b>Description:</b>
 * TODO
 * <p><b>Company:</b>
 *
 * @author created by Jesse Hsu at 9:31 on 2021/3/30
 * @version V0.1
 * @classNmae HannoverSolution
 */
public class HannoverSolution {

    public static void main(String[] args) {
        int[] palates = {3,2,1};
        HannoverSolution solution = new HannoverSolution();
        solution.hannoverSolution(2);
    }

    public void hannoverSolution(int level){
        hannover(level,'A','C','B');
    }

    public void hannover(int level,char from,char to,char mid){

        if(level==1){
            moveTo(from,to);
            return;
        }
        hannover(level-1,from,mid,to);
        moveTo(from,to);
        hannover(level-1,mid,to,from);

    }

    public void moveTo(char from,char to){
        System.out.println(from + "--->" + to);
    }
}
