package com.demo.leetcode;

import org.junit.Test;

public class SearchBSTSolution {


    @Test
    public void main() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2, n1, n3);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4, n2, n7);
        TreeNode n = searchBST(n4, 2);
        System.out.println(n);

    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        TreeNode node = val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
        return node;
    }
}
