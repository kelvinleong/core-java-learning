package multithreading;

import org.junit.jupiter.api.Test;

public class MultithreadingTest {
    private boolean flag;
    private volatile boolean vFlag;

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

    @Test
    public void testNonVolatileFlag() throws InterruptedException {
        // when you run this test case many times, you will find it behaves differently
        // sometimes, it just prints "I am done" and exit
        // sometimes, it never prints "I am done"
        Runnable r = () -> {
            while (!flag) {
                //Thread.yield();
            }
            System.out.println("I am done");
        };
        var t = new Thread(r);
        t.start();

        Runnable r1 = () -> flag = true;
        var t1 = new Thread(r1);
        t1.start();

        Thread.sleep(1000);
    }

    @Test
    public void testVolatileFlag() throws InterruptedException {
        Runnable r = () -> {
            while (!vFlag) {
                Thread.yield();
            }
            System.out.println("I am done");
        };
        var t = new Thread(r);
        t.start();

        Runnable r1 = () -> vFlag = true;
        var t1 = new Thread(r1);
        t1.start();
        Thread.sleep(2000);
    }
}
