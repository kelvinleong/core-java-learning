package algorithms;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTree {
    public class TreeNode {
        TreeNode left;
        TreeNode right;
        Integer val;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    private int height(TreeNode root) {
        if (root == null) return 0;

        return 1 + Math.max(height(root.left), height(root.right));
    }

    private void levelTraverse(TreeNode root, int level, List<Integer> completedBinaryTree) {
        if (root == null) {
            completedBinaryTree.add(null);
            return;
        }

        if (level == 1) {
            completedBinaryTree.add(root.val);
        } else {
            levelTraverse(root.left, level - 1, completedBinaryTree);
            levelTraverse(root.right, level - 1, completedBinaryTree);
        }
    }

    /**
     *   Level Order Traverse
     *   #1 Recursive way
     */
    public void recursiveTraverse(TreeNode root) {
        List<Integer> ins = new ArrayList<>();
        int height = height(root);
        for (int i = 1; i <= height; ++i) {
            levelTraverse(root, 1, ins);
        }
    }

    /**
     *   Level Order Traverse
     *   #1 FIFO (using a queue) way
     */
    public void fifoTraverse(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode head = q.poll();
            if (head != null) {
                tree.add(head.val);
                if (head.left != null) q.add(head.left);
                if (head.right != null) q.add(head.right);
            } else {
                tree.add(null);
            }
        }
    }
}
