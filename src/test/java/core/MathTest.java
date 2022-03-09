package core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathTest {
    @Test
    public void testIntToDouble() {
        int a = 3;
        double b = a / 2;
        assertEquals(1, b);

        a = 3;
        b = (double) a / 2;
        assertEquals(1.5, b);
    }
}
