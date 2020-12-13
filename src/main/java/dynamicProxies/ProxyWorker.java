package dynamicProxies;

public class ProxyWorker implements Worker {
    private RealWorker worker = new RealWorker();

    @Override
    public long increment() {
        return worker.increment();
    }

    @Override
    public void reduce() {
        worker.reduce();
    }
}
