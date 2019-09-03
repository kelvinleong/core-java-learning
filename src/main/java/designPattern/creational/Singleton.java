package designPattern.creational;

public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    private static class SingletonInstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }

    // thread unsafety
    public static Singleton getSimpleInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // thread safety but with performance drawbacks
    public static synchronized Singleton getInstanceWithSync() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static Singleton getInstanceWithLock() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static Singleton getInstanceWithPreload() {
        return SingletonInstanceHolder.INSTANCE;
    }
}
