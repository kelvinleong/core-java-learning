package core;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectorTest {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class MyObject {
        private List<String> list;
        private Integer version;
    }

    @Test
    public void testMergeGivenUniqueList() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JsonProcessingException {
        Projector<MyObject> aggregator = new Projector<>(MyObject.class);
        var arrays = List.of(MyObject.builder().list(List.of("foo")).version(1).build(), MyObject.builder().list(List.of("bar")).version(2).build());
        MyObject r = aggregator.aggregate(arrays, Projector.Policy.MERGE);
        assertEquals(2, r.getVersion());
        assertEquals(2, r.getList().size());
        assertEquals("foo", r.getList().get(0));
        assertEquals("bar", r.getList().get(1));
    }

    @Test
    public void testMergeGivenOverlappedList() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JsonProcessingException {
        Projector<MyObject> aggregator = new Projector<>(MyObject.class);
        var arrays = List.of(MyObject.builder().list(List.of("foo", "bar")).version(1).build(), MyObject.builder().list(List.of("bar", "bob")).version(2).build());
        MyObject r = aggregator.aggregate(arrays, Projector.Policy.MERGE);
        assertEquals(2, r.getVersion());
        assertEquals(3, r.getList().size());
        assertEquals("foo", r.getList().get(0));
        assertEquals("bar", r.getList().get(1));
        assertEquals("bob", r.getList().get(2));
    }

    @Test
    public void testReplace() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JsonProcessingException {
        Projector<MyObject> aggregator = new Projector<>(MyObject.class);
        var arrays = List.of(MyObject.builder().list(List.of("foo")).version(1).build(), MyObject.builder().list(List.of("bar")).version(2).build());
        MyObject r = aggregator.aggregate(arrays, Projector.Policy.REPLACE);
        assertEquals(2, r.getVersion());
        assertEquals(1, r.getList().size());
        assertEquals("bar", r.getList().get(0));
    }
}
