package leetcodeChallenge;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LRUCache {
    private int capacity;
    private AtomicInteger p = new AtomicInteger(1);
    private PriorityQueue<Entity> queue;
    private Map<Integer, Entity> map;

    private class Entity {
        private Integer key;
        private int value;
        private int priority;

        public Entity(Integer key, int value, int priority) {
            this.key = key;
            this.value = value;
            this.priority = priority;
        }

        public int getValue() { return value; }
        public int getPriority() { return priority; }
        public Integer getKey() { return key; }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.queue = new PriorityQueue<>(Comparator.comparing(Entity::getPriority));
        this.map = new HashMap<>();

    }

    private void set(int key, int value) {
        Entity o = new Entity(key, value, p.addAndGet(1));
        map.put(key, o);
        queue.add(o);
    }

    private void remove(int key) {
        queue.remove(map.get(key));
        map.remove(key);
    }

    public int get(int key) {
        return Optional.ofNullable(map.get(key))
                .map(e -> {
                    remove(key);
                    set(key, e.getValue());
                    return map.get(key);
                })
                .orElse(new Entity(0, -1, 0))
                .getValue();
    }

    public void put(int key, int value) {
        if (map.size() >= capacity && map.get(key) == null) {
            Entity head = queue.poll();
            if (head != null) {
                map.remove(head.getKey());
            }
        }
        remove(key);
        set(key, value);
    }
}

