package dynamicProxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ElapsedTimeHandler implements InvocationHandler {
    private Map<String, Method> methods = new HashMap<>();
    private Object target;

    public ElapsedTimeHandler(Object target) {
        this.target = target;
        for (Method m: target.getClass().getDeclaredMethods()) {
            this.methods.put(m.getName(), m);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        var o = methods.get(method.getName()).invoke(target, args);
        long end = System.currentTimeMillis();

        System.out.println("Elapsed time: " + (end - start) + "s");

        return o;
    }
}
