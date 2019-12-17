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
        if (level == 1) {
            completedBinaryTree.add(root == null ? null: root.val);
        } else {
            levelTraverse(root == null ? null : root.left, level - 1, completedBinaryTree);
            levelTraverse(root == null ? null : root.right, level - 1, completedBinaryTree);
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
        int height = height(root);
        int maxNoOfNodes = (int) Math.pow(2, height) - 1;
        List<Integer> tree = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode head = q.poll();
            tree.add(head == null ? null : head.val);
            if (tree.size() < maxNoOfNodes) {
                q.add(head == null ? null : head.left);
                q.add(head == null ? null : head.right);
            }
        }
    }

    public void initTree(List<Integer> l) {
        TreeNode root = new TreeNode(l.get(0));
        TreeNode head = root;
        for (int i = 0; i <= l.size()/ 2 - 1; ++i) {
            head.left = new TreeNode(l.get(2 * i));
            head.right = new TreeNode(l.get(2 * i + 1));
        }
    }
}
