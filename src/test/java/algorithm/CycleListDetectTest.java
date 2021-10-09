package algorithm;

import algorithms.CycleListDetect;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CycleListDetectTest {
    @Test
    public void testSingleElementCycleListDetect() {
        CycleListDetect.Node n1 = CycleListDetect.Node.builder().value(1).build();
        n1.setNext(n1);

        CycleListDetect dt = new CycleListDetect();
        assertTrue(dt.isCycleList(n1));
    }

    @Test
    public void testMultipleElementsCycleListDetect() {
        CycleListDetect.Node n1 = CycleListDetect.Node.builder().value(1).build();
        CycleListDetect.Node n2 = CycleListDetect.Node.builder().value(2).build();
        CycleListDetect.Node n3 = CycleListDetect.Node.builder().value(3).build();
        CycleListDetect.Node n4 = CycleListDetect.Node.builder().value(4).build();
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n1);

        CycleListDetect dt = new CycleListDetect();
        assertTrue(dt.isCycleList(n1));
    }

    @Test
    public void testNonCycleListDetect() {
        CycleListDetect.Node n1 = CycleListDetect.Node.builder().value(1).build();
        CycleListDetect.Node n2 = CycleListDetect.Node.builder().value(2).build();
        n1.setNext(n2);
        n2.setNext(null);

        CycleListDetect dt = new CycleListDetect();
        assertFalse(dt.isCycleList(n1));
    }
}
