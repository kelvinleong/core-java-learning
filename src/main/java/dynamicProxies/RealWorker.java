package dynamicProxies;

public class RealWorker implements Worker {
    private long counter = 0;

    @Override
    public long increment() {
        return ++counter;
    }

    @Override
    public void reduce() {
        --counter;
    }
}
