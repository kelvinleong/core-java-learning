package multithreading;

public class MultiThreading {
    private final Object shared1;
    private final Object shared2;

    public MultiThreading(final Object shared1, final Object shared2) {
        this.shared1 = shared1;
        this.shared2 = shared2;
    }

    private void execute() {
        System.out.println("execute a function");
    }

    public Runnable createThread1() {
        return () -> {
            synchronized (shared1) {
                System.out.println("Acquire lock on shared object 1 and sleep 10s");
                try {
                    Thread.sleep(10000);
                    synchronized (shared2) {
                        System.out.println("Acquire lock on shared object 2");
                        execute();
                        System.out.println("Release lock on shared object 2");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Release lock on shared object 2");
            }
        };
    }

    public Runnable createThread2() {
        return () -> {
            synchronized (shared2) {
                System.out.println("Acquire lock on shared object 2 and sleep 10s");
                try {
                    Thread.sleep(10000);
                    synchronized (shared1) {
                        System.out.println("Acquire lock on shared object 1");
                        execute();
                        System.out.println("Release lock on shared object 1");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Release lock on shared object 1");
            }
        };
    }
}
