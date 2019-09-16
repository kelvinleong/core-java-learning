import algorithms.Graph;
import algorithms.KadaneAlgorithm;
import dataStructure.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlgorithmTest {
    @Test
    public void should_Reach_when_TraverseGraph() {
        Graph g = new Graph();
        g.addEdge(2, 1);
        g.addEdge(2, 5);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(5, 1);
        assertTrue(g.reachable(2, 5));
    }

    @Test
    public void should_GetMaxValue_when_RunKadaneAlgo() {
        KadaneAlgorithm kadaneAlgorithm = new KadaneAlgorithm();
        List<Integer> list = List.of(1, -2, 1);
        Integer result = kadaneAlgorithm.findLargestContinguousSubArraySum(list);
        assertEquals(1, (int) result);
    }

    @Test
    public void shoud_PushAndPopValue_when_RunDinnerPlates() {
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
}
