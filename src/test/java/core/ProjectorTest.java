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

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class MyFinalObject {
        private String name;
        private Integer idNo;
        private MyObject object;
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

    @Test
    public void testMultipleMerge() throws JsonProcessingException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Projector<MyObject> aggregator = new Projector<>(MyObject.class);
        var arrays = List.of(MyObject.builder().list(List.of("foo", "bar")).version(1).build(),
                MyObject.builder().list(List.of("bar", "bob")).version(2).build(),
                MyObject.builder().list(List.of("alice", "peter")).version(2).build());

        MyObject r = aggregator.aggregate(arrays, Projector.Policy.MERGE);
        assertEquals(2, r.getVersion());
        assertEquals(5, r.getList().size());
        assertEquals("foo", r.getList().get(0));
        assertEquals("bar", r.getList().get(1));
        assertEquals("bob", r.getList().get(2));
        assertEquals("alice", r.getList().get(3));
        assertEquals("peter", r.getList().get(4));
    }

    @Test
    public void testMergeNestedObject() throws JsonProcessingException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Projector<MyFinalObject> aggregator = new Projector<>(MyFinalObject.class);
        var obj1 = MyObject.builder().list(List.of("bar", "bob")).version(2).build();
        var obj2 = MyObject.builder().list(List.of("bar", "foo")).version(1).build();
        var arrays = List.of(MyFinalObject.builder().name("kelvin").idNo(1).object(obj1).build(),
                MyFinalObject.builder().name("calvin").object(obj2).build());

        MyFinalObject r = aggregator.aggregate(arrays, Projector.Policy.MERGE);
        assertEquals(1, r.getIdNo());
        assertEquals("calvin", r.getName());
        assertEquals(1, r.getObject().getVersion());
        assertEquals(List.of("bar", "bob", "foo"), r.getObject().getList());
    }
}
