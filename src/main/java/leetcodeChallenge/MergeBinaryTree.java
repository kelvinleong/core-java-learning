package leetcodeChallenge;


import java.util.LinkedList;
import java.util.Queue;

public class MergeBinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    private TreeNode constructNode(TreeNode t1, TreeNode t2) {
        TreeNode node = new TreeNode(0);
        if (t1 == null && t2 == null) {
            return null;
        } else if (t1 != null && t2 == null) {
            node.val = t1.val;
        } else if (t1 == null) {
            node.val = t2.val;
        } else {
            node.val = t1.val + t2.val;
        }

        return node;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        Queue<TreeNode> q3 = new LinkedList<>();

        q1.add(t1);
        q2.add(t2);

        TreeNode t3 = constructNode(t1, t2);
        q3.add(t3);
        while (q1.size() > 0 || q2.size() > 0) {
            if (q1.size() > 0) {
                t1 = q1.poll();
            }

            if (q2.size() > 0) {
                t2 = q2.poll();
            }

            var tn3 = q3.poll();

            if (t1 != null) {
                q1.add(t1.left);
                q1.add(t1.right);
            }

            if (t2 != null) {
                q2.add(t2.left);
                q2.add(t2.right);
            }

            if (tn3 != null) {
                tn3.left = constructNode(t1 != null ? t1.left : null, t2 != null ? t2.left : null);
                tn3.right = constructNode(t1 != null ? t1.right : null, t2 != null ? t2.right : null);
                q3.add(tn3.left);
                q3.add(tn3.right);
            }
        }

        return t3;
    }
}
