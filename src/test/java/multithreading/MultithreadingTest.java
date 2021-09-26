package multithreading;

import org.junit.jupiter.api.Test;

public class MultithreadingTest {
    @Test
    public void testDeadLock() throws InterruptedException {
        var obj1 = new Object();
        var obj2 = new Object();
        MultiThreading mt = new MultiThreading(obj1, obj2);
        var t1 = new Thread(mt.createThread1());
        var t2 = new Thread(mt.createThread2());
        t1.start();
        t2.start();

        // sleep 200s and use jconsole/visuamvm to monitor the thread usage
        // from the thread dump, you could find the deadlock is detected
        Thread.sleep(200000);
    }
}
