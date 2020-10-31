package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class StringPoolTest {
    @Test
    public void testStringEqual() {
        String a = "test";
        String b = "test";
        String c = new String("test");
        String d = c.intern();

        assertSame(a, b);
        assertSame(a, d);
        assertSame(b, d);
        assertNotSame(a, c);
        assertNotSame(b, c);
        assertTrue(a.equals(c) && c.equals(d));
    }
}
