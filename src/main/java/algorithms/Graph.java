package algorithms;

import designPattern.creational.Tuple;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Graph {
    private List<Tuple<Integer, List<Integer>>> graph = new ArrayList<>();

    public void addEdge(final int source, final int dest) {
        graph.stream()
                .filter(t -> t.getFirst() == source)
                .findFirst()
                .map(v -> v.getSecond().add(dest))
                .or(() -> {
                    List<Integer> dests = new ArrayList<>();
                    dests.add(dest);
                    graph.add(Tuple.Builder.newInstance().first(source).second(dests).build());
                    return Optional.empty();
                });
    }

    private void dfs(int s, int d, Map<Integer, Boolean> visited, AtomicBoolean result) {
        if (visited.getOrDefault(s, true)) {
            return;
        }

        graph.forEach(ve -> {
            if (ve.getFirst() == s) {
                visited.put(s, true);
                ve.getSecond().forEach(dst -> {
                    if (dst == d) result.getAndSet(true);
                    System.out.println("v" + dst);
                    dfs(dst, d, visited, result);
                });
            }
        });
    }

    private Map<Integer, Boolean> visited() {
        Map<Integer, Boolean> visited = new HashMap<>();
        graph.forEach(v -> {
            visited.put(v.getFirst(), false);
            v.getSecond().forEach(s -> visited.put(s, false));
        });

        return visited;
    }

    public Boolean reachable(int s, int d) {
        var visited = visited();
        var result = new AtomicBoolean(false);
        dfs(s, d, visited, result);

        return result.get();
    }
}
