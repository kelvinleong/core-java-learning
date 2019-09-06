import algorithms.Graph;

public class Application {
    private static void graph() {
        Graph g = new Graph();
        g.addEdge(2, 1);
        g.addEdge(2, 5);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(5, 1);
        System.out.println("if it's reachable from 2 -> 5 == " + g.reachable(2, 5));
    }

    public static void main(String[] args) {
        graph();
    }
}
