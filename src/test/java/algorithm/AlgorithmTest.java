package algorithm;

import algorithms.*;
import dataStructure.*;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {
    @Test
    public void should_Reach_when_TraverseGraph() {
        Graph g = new Graph();
        g.addEdge(2, 1);
        g.addEdge(2, 5);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(5, 1);
        assertTrue(g.isReachable(2, 5));
    }

    @Test
    public void should_GetMaxValue_1_when_RunKadaneAlgo() {
        KadaneAlgorithm kadaneAlgorithm = new KadaneAlgorithm();
        List<Integer> list = List.of(1, -2, 1);
        Integer result = kadaneAlgorithm.findLargestContiguousSubArraySum(list);
        assertEquals(1, (int) result);
    }

    @Test
    public void should_GetMaxValue_10_when_RunKadaneAlgo() {
        KadaneAlgorithm kadaneAlgorithm = new KadaneAlgorithm();
        List<Integer> list = List.of(9, 1, -1);
        Integer result = kadaneAlgorithm.findLargestContiguousSubArraySum(list);
        assertEquals(10, (int) result);
    }

    @Test
    public void should_PushAndPopValue_when_RunDinnerPlates() {
        DinnerPlates dp = new DinnerPlates(2);
        dp.push(1);
        dp.push(2);
        dp.push(3);
        dp.push(4);
        dp.push(5);

        assertEquals(5, dp.pop());
        assertEquals(4, dp.pop());
        assertEquals(3, dp.pop());
        assertEquals(2, dp.pop());
        assertEquals(1, dp.pop());
    }

    @Test
    public void should_PopValue_when_GivenSpecificIndex() {
        DinnerPlates dp = new DinnerPlates(2);
        dp.push(1);
        dp.push(2);
        dp.push(3);
        dp.push(4);
        dp.push(5);

        assertEquals(2, dp.popAtStack(0));
    }

    @Test
    public void should_PushAndPopValue_when_PushAndPopFlushInBetween() {
        DinnerPlates dp = new DinnerPlates(2);
        dp.push(1);
        dp.push(2);
        dp.push(3);
        dp.push(4);
        dp.push(5);
        dp.popAtStack(0);
        dp.push(6);

        assertEquals(5, dp.pop());
        assertEquals(4, dp.pop());
        assertEquals(3, dp.pop());
        assertEquals(6, dp.pop());
        assertEquals(1, dp.pop());
    }

    @Test
    public void should_ConstructSTAndQuery_when_BuildSegmentTree() {
        SegmentTree<Integer> st = new SegmentTree<>(new Integer[]{1, 3, 2, 7, 9, 11});
        st.buildSegmentTree();
        Integer result = st.query(1, 5);
        assertEquals(result, 2);

        // update 1 -> 5 at index 0
        st.update(0, 5);
        result = st.query(0, 1);
        assertEquals(result, 3);

        // update 2 -> 8 at index 2
        st.update(2, 8);
        result = st.query(2, 5);
        assertEquals(result, 7);
    }

    @Test
    public void should_MatchSubString_when_UsingRollingHash() {
        RollingHash rh = new RollingHash();
        int idx  = rh.repeatedStringMatch("rollinginthedip", "dip");
        assertTrue(idx > 0);
    }

    @Test
    public void should_MatchRollingSubString_when_UsingRollingHash() {
        RollingHash rh = new RollingHash();
        int idx  = rh.repeatedStringMatch("cranberries", "scran");
        assertTrue(idx > 0);
    }

    @Test
    public void should_FailedMatchSubString_when_UsingRollingHash() {
        RollingHash rh = new RollingHash();
        int idx  = rh.repeatedStringMatch("nonthingleft", "some");
        assertTrue(idx == -1);
    }

    @Test
    public void should_RecursivelyOrderLevelTraverse_when_UsingRecursiveWay() {
        BinaryTree bt = new BinaryTree();
        var tree = bt.initTree(Arrays.asList(1, 2, 5));
        var treeArray = bt.recursiveTraverse(tree);
        assertEquals(3, treeArray.size());

        tree = bt.initTree(Arrays.asList(1, 2, null, 3, 4, null, null, 5));
        treeArray = bt.recursiveTraverse(tree);
        assertEquals(8, treeArray.size());
    }

    @Test
    public void should_OrderLevelTraverseByQueue_when_UsingRecursiveWay() {
        BinaryTree bt = new BinaryTree();
        var tree = bt.initTree(Arrays.asList(1, 2, 5));
        var treeArray = bt.fifoTraverse(tree);
        assertEquals(3, treeArray.size());

        tree = bt.initTree(Arrays.asList(1, 2, null, 3, 4, null, null, 5));
        treeArray = bt.recursiveTraverse(tree);
        assertEquals(8, treeArray.size());
    }

    @Test
    public void should_getTrue_when_RunIsPowerOfTwo() {
        CommonAlgoUtils commonAlgoUtils = new CommonAlgoUtils();
        Boolean r = commonAlgoUtils.isNumPowerOfTwo(2);
        assertTrue(r);

        r = commonAlgoUtils.isNumPowerOfTwo(4);
        assertTrue(r);

        r = commonAlgoUtils.isNumPowerOfTwo(8);
        assertTrue(r);

        r = commonAlgoUtils.isNumPowerOfTwo(16);
        assertTrue(r);
    }

    @Test
    public void should_getFalse_when_RunIsPowerOfTwo() {
        CommonAlgoUtils commonAlgoUtils = new CommonAlgoUtils();

        var r = commonAlgoUtils.isNumPowerOfTwo(1);
        assertFalse(r);

        r = commonAlgoUtils.isNumPowerOfTwo(5);
        assertFalse(r);

        r = commonAlgoUtils.isNumPowerOfTwo(10);
        assertFalse(r);
    }

    @Test
    public void should_getFourteen_when_RunKnapsack() {
        int[] values = {5, 9 , 3};
        int[] weights = {2, 3, 9};
        KnapsackProblem kp = new KnapsackProblem();
        var max = kp.knapsack(values, weights, 12, weights.length - 1);
        assertEquals(14, max);
    }

    @Test
    public void should_getFourteen_when_RunKnapsackDp() {
        int[] values = {5, 9 , 3};
        int[] weights = {2, 3, 9};
        KnapsackProblem kp = new KnapsackProblem();
        var max = kp.knapsackUsingDp(values, weights, 12);
        assertEquals(14, max);
    }

    @Test
    public void should_getIndexes_when_RunKmp() {
        String s = "abcabcabc";
        String p = "abc";
        KmpPattern kmp = new KmpPattern();
        var r = kmp.kmpSearch(s, p);
        assertEquals(3, r.size());
        assertEquals(0, r.get(0));
        assertEquals(3, r.get(1));
        assertEquals(6, r.get(2));
    }

    @Test
    public void should_returnTrue_when_GivenValidPowerNumber() {
        CommonAlgoUtils commonAlgoUtils = new CommonAlgoUtils();
        assertTrue(commonAlgoUtils.isNumPowerOfN(5, 25));
        assertTrue(commonAlgoUtils.isNumPowerOfN(3, 9));
        assertTrue(commonAlgoUtils.isNumPowerOfN(12, 144));
        assertTrue(commonAlgoUtils.isNumPowerOfN(19, 361));
    }

    @Test
    public void should_returnFalse_when_GivenInvalidValidPowerNumber() {
        CommonAlgoUtils commonAlgoUtils = new CommonAlgoUtils();
        assertFalse(commonAlgoUtils.isNumPowerOfN(5, 26));
        assertFalse(commonAlgoUtils.isNumPowerOfN(4, 9));
        assertFalse(commonAlgoUtils.isNumPowerOfN(11, 144));
        assertFalse(commonAlgoUtils.isNumPowerOfN(18, 361));
    }

    @Test
    public void should_returnTrue_when_GivenRepeatedPatternString() {
        CommonAlgoUtils commonAlgoUtils = new CommonAlgoUtils();
        assertTrue(commonAlgoUtils.isRepeatedPattern("kelvin", "nkelvi"));
        assertTrue(commonAlgoUtils.isRepeatedPattern("kelvin", "kelvin"));
    }

    @Test
    public void should_returnFalse_when_GivenRepeatedPatternString() {
        CommonAlgoUtils commonAlgoUtils = new CommonAlgoUtils();
        assertFalse(commonAlgoUtils.isRepeatedPattern("aa", "bb"));
    }

    @Test
    public void should_getTheOnlyNumber_when_GivenOnlyOneOfWhichOccursOddTimesInTheArray() {
        CommonAlgoUtils commonAlgoUtils = new CommonAlgoUtils();
        assertEquals(4, commonAlgoUtils.theOnlyNumberThatOccurOddTimes(new int[]{2, 2, 3, 3, 4}));
        assertEquals(4, commonAlgoUtils.theOnlyNumberThatOccurOddTimes(new int[]{3, 3, 3, 3, 4}));
    }

    @Test
    public void should_getShortestPath_when_GivenValidGraph() {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
        dijkstraAlgorithm.edge("a", "b", 9);
        dijkstraAlgorithm.edge("a", "c", 5);
        dijkstraAlgorithm.edge("b", "e", 2);
        dijkstraAlgorithm.edge("c", "b", 1);
        dijkstraAlgorithm.edge("c", "d", 3);

        dijkstraAlgorithm.solve("a");
        assertEquals("b->c->a", dijkstraAlgorithm.routeFromSrcToDstAndLength("a", "b").get(0));
        assertEquals("6", dijkstraAlgorithm.routeFromSrcToDstAndLength("a", "b").get(1));

        assertEquals("c->a", dijkstraAlgorithm.routeFromSrcToDstAndLength("a", "c").get(0));
        assertEquals("5", dijkstraAlgorithm.routeFromSrcToDstAndLength("a", "c").get(1));

        assertEquals("d->c->a", dijkstraAlgorithm.routeFromSrcToDstAndLength("a", "d").get(0));
        assertEquals("8", dijkstraAlgorithm.routeFromSrcToDstAndLength("a", "d").get(1));

        assertEquals("e->b->c->a", dijkstraAlgorithm.routeFromSrcToDstAndLength("a", "e").get(0));
        assertEquals("8", dijkstraAlgorithm.routeFromSrcToDstAndLength("a", "e").get(1));
    }
}
