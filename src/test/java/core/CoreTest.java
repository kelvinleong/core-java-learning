package core;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CoreTest {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    protected static class MyObject implements Cloneable {
        private List<String> a;
        private String b;

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    @Test
    public void testShallowCopy() throws CloneNotSupportedException {
        var obj = MyObject.builder().a(Lists.newArrayList("a")).b("b").build();
        var clone = (MyObject) obj.clone();
        clone.getA().add("c");
        clone.setB("d");
        assertEquals(2, obj.getA().size());
        assertEquals("d", clone.getB());
        assertEquals("b", obj.getB());
    }

    @Test
    public void testDeepCopy() throws CloneNotSupportedException {
        var gson = new Gson();
        var obj = MyObject.builder().a(Lists.newArrayList("a")).b("c").build();
        var deepClone = gson.fromJson(gson.toJson(obj), MyObject.class);
        deepClone.getA().add("b");
        assertEquals(1, obj.getA().size());
        assertEquals(2, deepClone.getA().size());
    }

    @Test
    public void testCompletableFuture() {
        var tasks = List.of("task1", "task2", "task3");
        CompletableFuture<String>[] futures = new CompletableFuture[tasks.size()];
        for (int i = 0; i < tasks.size(); ++i) {
            var result = tasks.get(i) + "_result";
            futures[i] = CompletableFuture.supplyAsync(() -> result);
        }
        var r = CompletableFuture.allOf(futures);
        try {
            r.get();
            Set<String> rs = new HashSet<>();
            for (CompletableFuture<String> f: futures) {
                rs.add(f.get());
            }
            assertEquals(3, rs.size());
            assertTrue(rs.contains("task1_result"));
            assertTrue(rs.contains("task2_result"));
            assertTrue(rs.contains("task3_result"));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCountDownLatch() throws InterruptedException {
        var tasks = List.of("task1", "task2", "task3");
        var limit = new CountDownLatch(3);
        var executor = Executors.newFixedThreadPool(3);
        var futures = tasks.stream().map(task -> executor.submit(() -> {
            limit.countDown();
            return task + "_result";
        })).collect(Collectors.toList());
        limit.await();
        try {
            Set<String> rs = new HashSet<>();
            for (Future<String> f: futures) {
                rs.add(f.get());
            }
            assertEquals(3, rs.size());
            assertTrue(rs.contains("task1_result"));
            assertTrue(rs.contains("task2_result"));
            assertTrue(rs.contains("task3_result"));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void comparePerformanceBetweenCountDownLatchAndFutures() throws InterruptedException {
        for (int i = 0; i < 500; ++i) {
            long start = System.currentTimeMillis();
            testCountDownLatch();
            long end = System.currentTimeMillis();
            System.out.println("countdown latch time elapsed: " + (end - start) + "ms");

            start = System.currentTimeMillis();
            testCompletableFuture();
            end = System.currentTimeMillis();
            System.out.println("completable futures time elapsed: " + (end - start) + "ms");
        }
    }

    @Test
    public void shouldCatchExceptionBySupplier() {
        String s = "kelvin";
        Supplier<String> supplier = () -> {
            if ("kelvin".equals(s)) {
                throw new RuntimeException("runtime exception");
            }
            return s;
        };

        assertThrowsExactly(RuntimeException.class, supplier::get, "runtime exception");
    }
}
