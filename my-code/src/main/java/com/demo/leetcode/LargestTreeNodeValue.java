package com.demo.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LargestTreeNodeValue {

    public static void main(String[] args) {
        LargestTreeNodeValue solution = new LargestTreeNodeValue();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(9);
        node1.left =node3;
        node1.right = node4;
        node2.right = node5;
        List<Integer> maxList = solution.largestValues(root);
        for (Integer max:maxList){
            System.out.print(max + ",");
        }
    }

    public List<Integer> largestValues(TreeNode root) {

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        List<Integer> maxList =new ArrayList<>() ;
        if(root!=null){
            nodeQueue.offer(root);
        }
        while (!nodeQueue.isEmpty()){
            int size = nodeQueue.size();
            int max =Integer.MIN_VALUE;
            for (int i = 0; i<size;i++){
                TreeNode node = nodeQueue.poll();
                if(null!=node.left){
                    nodeQueue.offer(node.left);
                }
                if(null!=node.right){
                    nodeQueue.offer(node.right);
                }
                max = Math.max(max,node.val);
            }
            maxList.add(max);
        }
        return maxList;
    }
}
