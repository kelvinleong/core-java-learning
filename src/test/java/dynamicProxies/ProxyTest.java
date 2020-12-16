package dynamicProxies;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProxyTest {
    @Test
    public void directCreate() {
        Worker worker = new RealWorker();
        long count = worker.increment();
        assertEquals(1L, count);
    }

    @Test
    public void proxyCreate() {
        Worker worker = new ProxyWorker();
        long count = worker.increment();
        assertEquals(1L, count);
    }

    @Test
    public void dynamicDirectProxyCreate() {
        var realWorker = new RealWorker();
        Worker dynamicWorker = (Worker) Proxy.newProxyInstance(Worker.class.getClassLoader(),
                new Class<?>[] {Worker.class},
                (proxy, method, args) -> realWorker.increment());
        long count = dynamicWorker.increment();
        assertEquals(1L, count);
    }

    @Test
    public void dynamicReflectiveProxyCreate() {
        var realWorker = new RealWorker();
        Worker dynamicWorker = (Worker) Proxy.newProxyInstance(Worker.class.getClassLoader(),
                new Class<?>[] {Worker.class},
                (proxy, method, args) -> method.invoke(realWorker, args));
        long count = dynamicWorker.increment();
        assertEquals(1L, count);
    }

    @Test
    public void hashMapElapsedTimeHandler() {
        Map mapProxy = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(),
                    new Class<?>[] {Map.class},
                    new ElapsedTimeHandler(new HashMap<>())
                );

        mapProxy.put("hello", "test");
        assertEquals("test", mapProxy.get("hello"));
    }
}
