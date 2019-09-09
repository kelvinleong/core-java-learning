import algorithms.Graph;
import designPattern.behavioral.composite.*;

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

    private static void composite() {
        Divide exp = new Divide(new Multiply(new Negate(5.0), new Multiply(new Constants(9.0), new Constants(6.0))),
                                new Add(new Constants(7.0), new Subtract(new Constants(2.0), new Constants(1.5))));
        System.out.println("the value is : " + exp.calculate());
    }

    public static void main(String[] args) {
        composite();
    }
}
