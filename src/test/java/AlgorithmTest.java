import algorithms.Graph;
import org.junit.jupiter.api.Test;

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
}
