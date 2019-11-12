import leetcodeChallenge.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChanllengeTest {
    @Test
    public void should_CountBallons_when_RunBallonProblems() {
        BallonProblems bp = new BallonProblems();
        int result = bp.howManyBallons("ballonballonballonballonballonballonballonballonballonballon");
        assertEquals(result, 10);
    }

    @Test
    public void should_CountPalindrome_when_RunParlindrome() {
        LongestChunkedPalindrome lcp = new LongestChunkedPalindrome();
        int result = lcp.count("ghiabcdefhelloadamhelloabcdefghi");
        assertEquals(result, 7);
    }

    @Test
    public void should_FindOccurences_when_RunUniqueOccurences() {
        UniqueOccurences uq = new UniqueOccurences();
        boolean r = uq.uniqueOccurrences(new int[]{1, 3});
        assertEquals(r, false);
    }

    @Test
    public void should_mergeTree_when_RunMergeBinaryTree() {
        MergeBinaryTree.TreeNode t1 = new MergeBinaryTree.TreeNode(1);
        MergeBinaryTree.TreeNode t2 = new MergeBinaryTree.TreeNode(2);

        var head1 = t1;
        head1.left = new MergeBinaryTree.TreeNode(3);
        head1.right = new MergeBinaryTree.TreeNode(2);
        head1.right.left = null;
        head1.right.right = null;
        head1 = head1.left;
        head1.left = new MergeBinaryTree.TreeNode(5);
        head1.right = null;

        t2.left = new MergeBinaryTree.TreeNode(1);
        t2.left.left = null;
        t2.left.right = new MergeBinaryTree.TreeNode(4);
        t2.right = new MergeBinaryTree.TreeNode(3);
        t2.right.left = null;
        t2.right.right = new MergeBinaryTree.TreeNode(7);

        var mergeTree = new MergeBinaryTree();
        var result = mergeTree.mergeTrees(t1, t2);

        assertEquals(result.val, 3);
        assertEquals(result.left.val, 4);
        assertEquals(result.right.val, 5);
        assertEquals(result.left.left.val, 5);
        assertEquals(result.left.right.val, 4);
        assertEquals(result.right.right.val, 7);
    }

    @Test
    public void should_GetMinusOne_when_RunLRUCache() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        assertEquals(lruCache.get(2), -1);
    }

    @Test
    public void should_GetSix_when_RunLRUCache() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.get(2);
        lruCache.put(2, 6);
        lruCache.get(1);
        lruCache.put(1, 5);
        lruCache.put(1, 2);
        lruCache.get(1);
        assertEquals(lruCache.get(2), 6);
    }
}
