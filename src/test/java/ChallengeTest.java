import algorithms.EightQueens;
import leetcodeChallenge.*;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChallengeTest {
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

    @Test
    public void should_passTwitterExample1_when_RunTwitter() {
        Twitter twitter = new Twitter();

        // User 1 posts a new tweet (id = 5).
        twitter.postTweet(1, 5);

        // User 1's news feed should return a list with 1 tweet id -> [5].
        var result = twitter.getNewsFeed(1);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0));

        // User 1 follows user 2.
        twitter.follow(1, 2);

        // User 2 posts a new tweet (id = 6).
        twitter.postTweet(2, 6);

        // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
        // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        result = twitter.getNewsFeed(1);
        assertEquals(2, result.size());
        assertEquals(6, result.get(0));
        assertEquals(5, result.get(1));

        // User 1 unfollows user 2.
        twitter.unfollow(1, 2);

        // User 1's news feed should return a list with 1 tweet id -> [5],
        // since user 1 is no longer following user 2.
        result = twitter.getNewsFeed(1);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0));
    }

    @Test
    public void should_passTwitterExample2_when_RunTwitter() {
        Twitter twitter = new Twitter();

        twitter.postTweet(2, 5);
        twitter.postTweet(1, 6);
        twitter.postTweet(2, 7);
        twitter.postTweet(1, 8);
        twitter.follow(1, 2);

        var result = twitter.getNewsFeed(1);
        assertEquals(4, result.size());
        assertEquals(8, result.get(0));
        assertEquals(7, result.get(1));
        assertEquals(6, result.get(2));
        assertEquals(5, result.get(3));
    }

    @Test
    public void should_passTwitterExample3_when_RunTwitter() {
        Twitter twitter = new Twitter();

        twitter.postTweet(2, 5);
        twitter.postTweet(2, 7);

        var result = twitter.getNewsFeed(2);
        assertEquals(2, result.size());
        assertEquals(7, result.get(0));
        assertEquals(5, result.get(1));
    }

    @Test
    public void should_passRotateExample1_when_RunRotate() {
        RotateRight.ListNode n1 = new RotateRight.ListNode(0);
        RotateRight.ListNode n2 = new RotateRight.ListNode(1);
        RotateRight.ListNode n3 = new RotateRight.ListNode(2);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(null);

        RotateRight rr = new RotateRight();
        var r = rr.rotateRight(n1, 4);
        var head = r;
        var count = 0;
        int[] expected = {2, 0, 1};

        assertTrue(head != null);
        while(head != null) {
            assertEquals(expected[count], head.getVal());
            head = head.getNext();
            ++count;
        }
    }

    @Test
    public void should_passRemoveDigits_when_RunRemoveDigits() {
        RemoveDigits rd = new RemoveDigits();
        var result = rd.solution("111111", 2);
        assertEquals("1111", result);

        result = rd.solution("1234567890", 9);
        assertEquals("0", result);

        result = rd.solution("10200", 2);
        assertEquals("0", result);

        result = rd.solution("1432219", 3);
        assertEquals("1219", result);

        result = rd.solution("112", 1);
        assertEquals("11", result);

        result = rd.solution("1333795118424297109718941273592510556281286476952957142060176679385689990596069563482833814436779303673374352725999828678439", 111);
        assertEquals("222439", result);

        result = rd.solution("9999999999991", 8);
        assertEquals("99991", result);

        result = rd.solution("222222222222222222222210", 12);
        assertEquals("222222222210", result);
    }

    @Test
    public void should_decodeString_when_RunDecodeString() {
        DecodeString ds = new DecodeString();
        var result = ds.decode("3[a2[c]]");
        assertEquals("accaccacc", result);

        result = ds.decode("3[a]2[bc]");
        assertEquals("aaabcbc", result);

        result = ds.decode("2[abc]3[cd]ef");
        assertEquals("abcabccdcdcdef", result);

        result = ds.decode("abcdef");
        assertEquals("abcdef", result);

        result = ds.decode("3[z]2[2[y]pq4[2[jk]e1[f]]]ef");
        assertEquals("zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef", result);
    }

    @Test
    public void should_getSolution_when_RunEightQueensBT() {
        EightQueens eightQueens = new EightQueens();
        Boolean result = eightQueens.solve();
        assertEquals(true, result);
    }

    @Test
    public void should_pass_when_RunCombinationSum() {
        CombinationSum combinationSum = new CombinationSum();
        combinationSum.solve(new int[]{2, 3, 5}, 10);
    }
}
