package algorithms;

import designPattern.creational.Tuple;

import java.util.*;

public class DijkstraAlgorithm {
    private Map<String, List<Tuple<String, Integer>>> graph;
    private Map<String, Integer> dist;
    private Map<String, String> prev;

    public DijkstraAlgorithm() {
        graph = new HashMap<>();
        dist = new HashMap<>();
        prev = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public void edge(String src, String dst, Integer weight) {
        graph.computeIfAbsent(src, k -> new ArrayList<>())
                .add(Tuple.Builder.newInstance().first(dst).second(weight).build());
    }

    /**
     * Pseudocode:
     *
     *  create vertex set Q
     *
     *  for each vertex v in Graph:
     *      dist[v] ← INFINITY
     *      prev[v] ← UNDEFINED
     *      add v to Q
     *  dist[source] ← 0
     *
     *  while Q is not empty:
     *      u ← vertex in Q with min dist[u]
     *
     *      remove u from Q
     *
     *      for each neighbor v of u:           // only v that are still in Q
     *          alt ← dist[u] + length(u, v)
     *          if alt < dist[v]:
     *              dist[v] ← alt
     *              prev[v] ← u
     *
     *  return dist[], prev[]
     */
    public void solve(String src) {
        List<String> q = new ArrayList<>();

        graph.forEach((key, value) -> {
            dist.put(key, Integer.MAX_VALUE);
            value.forEach(v -> dist.put(v.getFirst(), Integer.MAX_VALUE));
            q.add(key);
        });

        dist.put(src, 0);

        while(!q.isEmpty()) {
            dist.entrySet().stream()
                    .filter(e -> q.contains(e.getKey()))
                    .min(Map.Entry.comparingByValue())
                    .ifPresent(u -> {
                       q.remove(u.getKey());
                       graph.get(u.getKey()).forEach(t -> {
                            int alt = dist.get(u.getKey()) + t.getSecond();
                            if (alt < dist.get(t.getFirst())) {
                                dist.put(t.getFirst(), alt);
                                prev.put(t.getFirst(), u.getKey());
                            }
                       });
                    });
        }
    }

    public List<String> routeFromSrcToDstAndLength(String src, String dst) {
       String n = dst;
       List<String> route = new ArrayList<>();
       while (!prev.get(n).equals(src)) {
          route.add(n);
          n = prev.get(n);
       }
       route.add(n);
       route.add(src);

       return List.of(String.join("->", route), dist.get(dst).toString());
    }
}
