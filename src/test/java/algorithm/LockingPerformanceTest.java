package algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class LockingPerformanceTest {
    private ReentrantLock lock;
    private List<String> bigList;

    public LockingPerformanceTest() {
        lock = new ReentrantLock();
    }

    @BeforeEach
    public void init() {
        bigList = new ArrayList<>();
        for(int i = 0; i < 1000000; i++) {
            bigList.add("ID" + i);
        }
    }

    private void transform(String s) {
        s = "T" + s;
    }

    @Test
    public void bulkLock() {
        long start;
        long end;
        long t = 0l;

        for (int i = 0; i < 3; i++) {
            start = System.currentTimeMillis();
            lock.lock();
            for (String s : bigList) {
                transform(s);
            }
            lock.unlock();
            end = System.currentTimeMillis();

            t += (end - start);
            System.out.println("Processing time:" + (end - start) + "ms");
        }

        System.out.println("Average processing time: " + t/3 + "ms");
    }

    @Test
    public void individualLock() {
        long start;
        long end;
        long t = 0l;

        for (int i = 0; i < 3; i++) {
            start = System.currentTimeMillis();
            for (String s : bigList) {
                lock.lock();
                transform(s);
                lock.unlock();
            }
            end = System.currentTimeMillis();

            t += (end - start);
            System.out.println("Processing time:" + (end - start) + "ms");
        }

        System.out.println("Average processing time: " + t/3 + "ms");
    }
}
