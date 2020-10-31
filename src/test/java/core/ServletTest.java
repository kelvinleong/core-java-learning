package core;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ServletTest {
    @Test
    public void should_PrintSomething_when_InvokeDispatcher() {
        try {
            Servlet svr = new Servlet();
            var obj = (Integer) svr.dispatcher("/reconnect/solve", "[1,3,5,7,9,11,13,15]");
            assertEquals(66, obj.intValue());
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException |
                InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
