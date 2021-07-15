package functional;

import lombok.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionTest {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class TestObject {
        String s;
        Map<String, String> p;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Constant {
        int priority1;
        int priority2;
        int priority3;
        int priority4;
    }

    @Test
    public void testMapChainWithoutIfCondition() {
        var o = new TestObject();
        var r = Optional.of(o)
                .map(TestObject::getP)
                .map(m -> m.getOrDefault("k", "v"))
                .orElse("r");

        assertEquals("r", r);
    }

    /*
     *  if (o.getField() != null && o.getField().equals(xx)
     */
    public void check() {
        var o = new TestObject();
        var r = Optional.ofNullable(o)
                .map(TestObject::getP)
                .map(m -> m.getOrDefault("k", "v"))
                .orElse("r");

        assertEquals("r", r);
    }

    @Test
    public void testFlatMap() {
        var compound = Arrays.asList(List.of("a"), List.of("b"));
        var set = compound.stream()
                .flatMap(Collection::stream)
                .map(String::toUpperCase)
                .collect(Collectors.toSet());

        assertTrue(set.contains("A"));
        assertTrue(set.contains("B"));
    }

    @Test
    public void testPriorityMapChain() {
        // case0
        var constant = Constant.builder().build();
        var v1 = Optional.ofNullable(constant.getPriority1() == 0 ? null : constant.getPriority1());
        var v2 = Optional.ofNullable(constant.getPriority2() == 0 ? null : constant.getPriority2());
        var v3 = Optional.ofNullable(constant.getPriority3() == 0 ? null : constant.getPriority3());
        var v4 = Optional.ofNullable(constant.getPriority4() == 0 ? null : constant.getPriority4());
        var r = Stream.of(v1, v2, v3, v4)
                .flatMap(Optional::stream)
                .findFirst()
                .orElse(10);
        assertEquals(10, r);

        // case1
        constant = Constant.builder().priority1(1).build();
        v1 = Optional.ofNullable(constant.getPriority1() == 0 ? null : constant.getPriority1());
        v2 = Optional.ofNullable(constant.getPriority2() == 0 ? null : constant.getPriority2());
        v3 = Optional.ofNullable(constant.getPriority3() == 0 ? null : constant.getPriority3());
        v4 = Optional.ofNullable(constant.getPriority4() == 0 ? null : constant.getPriority4());
        r = Stream.of(v1, v2, v3, v4)
                .flatMap(Optional::stream)
                .findFirst()
                .orElse(10);
        assertEquals(1, r);

        // case2
        constant = Constant.builder().priority2(2).build();
        v1 = Optional.ofNullable(constant.getPriority1() == 0 ? null : constant.getPriority1());
        v2 = Optional.ofNullable(constant.getPriority2() == 0 ? null : constant.getPriority2());
        v3 = Optional.ofNullable(constant.getPriority3() == 0 ? null : constant.getPriority3());
        v4 = Optional.ofNullable(constant.getPriority4() == 0 ? null : constant.getPriority4());
        r = Stream.of(v1, v2, v3, v4)
                .flatMap(Optional::stream)
                .findFirst()
                .orElse(10);
        assertEquals(2, r);

        // case3
        constant = Constant.builder().priority3(3).build();
        v1 = Optional.ofNullable(constant.getPriority1() == 0 ? null : constant.getPriority1());
        v2 = Optional.ofNullable(constant.getPriority2() == 0 ? null : constant.getPriority2());
        v3 = Optional.ofNullable(constant.getPriority3() == 0 ? null : constant.getPriority3());
        v4 = Optional.ofNullable(constant.getPriority4() == 0 ? null : constant.getPriority4());
        r = Stream.of(v1, v2, v3, v4)
                .flatMap(Optional::stream)
                .findFirst()
                .orElse(10);
        assertEquals(3, r);

        // case4
        constant = Constant.builder().priority4(4).build();
        v1 = Optional.ofNullable(constant.getPriority1() == 0 ? null : constant.getPriority1());
        v2 = Optional.ofNullable(constant.getPriority2() == 0 ? null : constant.getPriority2());
        v3 = Optional.ofNullable(constant.getPriority3() == 0 ? null : constant.getPriority3());
        v4 = Optional.ofNullable(constant.getPriority4() == 0 ? null : constant.getPriority4());
        r = Stream.of(v1, v2, v3, v4)
                .flatMap(Optional::stream)
                .findFirst()
                .orElse(10);
        assertEquals(4, r);

    }
}
