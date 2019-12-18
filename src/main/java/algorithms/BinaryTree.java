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
        TreeNode(int val) {
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

    private List<Integer> trimEndingNull(List<Integer> l) {
        for (int i = l.size() - 1; i > 0; --i) {
           if (l.get(i) == null) {
               l.remove(i);
           } else {
               break;
           }
        }

        return l;
    }

    /**
     *   Level Order Traverse
     *   #1 Recursive way
     */
    public List<Integer> recursiveTraverse(TreeNode root) {
        List<Integer> tree = new ArrayList<>();
        int height = height(root);
        for (int i = 1; i <= height; ++i) {
            levelTraverse(root, i, tree);
        }
        return trimEndingNull(tree);
    }

    /**
     *   Level Order Traverse
     *   #2 FIFO (using a queue) way
     */
    public List<Integer> fifoTraverse(TreeNode root) {
        int height = height(root);
        int maxNoOfNodes = (int) Math.pow(2, height) - 1;
        List<Integer> tree = new ArrayList<>();
        Deque<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode head = q.poll();
            if (tree.size() < maxNoOfNodes) {
                tree.add(head == null ? null : head.val);
                q.add(head == null ? null : head.left);
                q.add(head == null ? null : head.right);
            }
        }

        return trimEndingNull(tree);
    }

    public TreeNode initTree(List<Integer> l) {
        TreeNode root = new TreeNode(l.get(0));
        Deque<TreeNode> q = new LinkedList<>();
        q.add(root);
        for (int i = 0; i <= (l.size() - 1)/ 2; ++i) {
            var head = q.poll();
            if (head != null) {
                if (2 * i + 1 < l.size()) {
                    head.left = l.get(2 * i + 1) == null ? null : new TreeNode(l.get(2 * i + 1));
                }

                if (2 * i + 2 < l.size()) {
                    head.right = l.get(2 * i + 2) == null ? null : new TreeNode(l.get(2 * i + 2));
                }

                q.add(head.left);
                q.add(head.right);
            }
        }

        return root;
    }
}
