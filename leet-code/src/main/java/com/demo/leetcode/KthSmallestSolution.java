package com.demo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 */
public class KthSmallestSolution {

    public static void main(String[] args) {
        TreeNode right_2 = new TreeNode(2);
        TreeNode left = new TreeNode(1, null, right_2);
        TreeNode right = new TreeNode(4);
        TreeNode root = new TreeNode(3, left, right);
        KthSmallestSolution solution = new KthSmallestSolution();
        System.out.println(solution.kthSmallest(root, 1));
        System.out.println(solution.inorderTraversal(root));

    }


    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> valList = inorderTraversal(root);
        midPrint(root, valList);
        return valList.get(k - 1);
    }

    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> valList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            valList.add(node.val);
            cur = node.right;
        }
        return valList;

    }

    public void midPrint(TreeNode root, List<Integer> valList) {
        if (root == null) {
            return;
        }
        midPrint(root.left, valList);
        valList.add(root.val);
        midPrint(root.right, valList);
    }
}
