package cqrs.eventSource.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventStore {
    private Map<String, List<Event>> store = new HashMap<>();

    public void addEvent(String userId, Event event) {
        store.computeIfAbsent(userId, v -> new ArrayList<>()).add(event);
    }

    public List<Event> getEventByUserId(String id) {
        return store.getOrDefault(id, new ArrayList<>());
    }
}
