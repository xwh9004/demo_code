package com.demo.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author: xwh90
 * @date: 2024/8/11 16:48
 * @description:
 */
public class LeetCode116_117 {


    public static void main(String[] args) {
        LeetCode116_117 leetCode116 = new LeetCode116_117();
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        final Node connect = leetCode116.connect(root);
        System.out.println(connect);
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node head = root;
        while (head.left != null) {
            Node node = head;
            while (node != null) {
                node.left.next = node.right;
                if(node.next!=null){
                    node.right.next = node.next.left;
                }
                node = node.next;
            }
            head = head.left;
        }
        return root;
    }

    public Node connect2(Node root) {
        doConn(root);
        return root;
    }

    public void doConn(Node root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                if (root.next != null) {
                    root.left.next = root.next.left != null ? root.next.left : root.next.right;
                }
            }

        }
        if (root.next != null) {
            if (root.right != null) {
                root.right.next = root.next.left != null ? root.next.left : root.next.right;
            }
        }
        doConn(root.left);
        doConn(root.right);
    }

    /**
     * 输入：root = [1,2,3,4,5,6,7]
     * 输出：[1,#,2,3,#,4,5,6,7,#]
     * 解释：给定二叉树如图 A 所示，
     * 你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
     * 序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束
     * <p>
     * 输入：root = []
     * 输出：[]
     *
     * @param root
     * @return
     */
    public Node connect0(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
        }
        return root;
    }

}
