package com.demo.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RobSolution3 {

    public static void main(String[] args) {

        RobSolution3 solution3 = new RobSolution3();
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        node1.left =node3;
        node1.right = node4;
        TreeNode node5 = new TreeNode(1);
        node2.right =node5;
        System.out.println(solution3.rob(root));
    }

    public int rob(TreeNode root) {
        int[] rootStatus = dfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left=dfs(node.left);
        int[] right=dfs(node.right);

        int selected = node.val +left[1] +right[1];
        int notSelected =Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        return new int[]{selected,notSelected};

    }


}
