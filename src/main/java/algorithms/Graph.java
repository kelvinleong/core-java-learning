package algorithms;

import designPattern.creational.Tuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Graph {
    private List<Tuple<Integer, List<Integer>>> graph = new ArrayList<>();

    public void addEdge(final int source, final int dest) {
        graph.stream()
                .filter(t -> t.getFirst() == source)
                .findFirst()
                .map(t -> t.getSecond().add(dest))
                .or(() -> {
                    graph.add(Tuple.Builder.newInstance().first(source).second(Arrays.asList(dest)).build());
                    return Optional.of(false);
                });
    }

    public void traverse(int verticle, List<List<Integer>> list, int index) {
        graph.forEach(g -> {
            if (g.getFirst() == verticle) {
                g.getSecond().forEach(v -> {
                    list.get(index).add(v);
                });
            }
        });
    }
}
