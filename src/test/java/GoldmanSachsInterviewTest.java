import interviews.GoldmanSachs;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class GoldmanSachsInterviewTest {
    @Test
    public void should_get7over6_when_given1over2and2over3() {
        GoldmanSachs gs = new GoldmanSachs();
        int[] r = gs.addFractions(new int[] {1, 2},  new int[] {2, 3});
        assertEquals(7, r[0]);
        assertEquals(6, r[1]);
    }

    @Test
    public void should_get12over35_when_given1over5and1over7() {
        GoldmanSachs gs = new GoldmanSachs();
        int[] r = gs.addFractions(new int[] {1, 5},  new int[] {1, 7});
        assertEquals(12, r[0]);
        assertEquals(35, r[1]);

        r = gs.addFractions(new int[] {-1, -5},  new int[] {-1, -7});
        assertEquals(12, r[0]);
        assertEquals(35, r[1]);
    }

    @Test
    public void should_getNeg12over35_when_givenNeg1over5andNeg1over7() {
        GoldmanSachs gs = new GoldmanSachs();
        int[] r = gs.addFractions(new int[] {-1, 5},  new int[] {-1, 7});
        assertEquals(-12, r[0]);
        assertEquals(35, r[1]);

        r = gs.addFractions(new int[] {1, -5},  new int[] {-1, 7});
        assertEquals(-12, r[0]);
        assertEquals(35, r[1]);

        r = gs.addFractions(new int[] {1, -5},  new int[] {1, -7});
        assertEquals(-12, r[0]);
        assertEquals(35, r[1]);
    }

    @Test
    public void should_getNeg2over35_when_givenNeg1over5and1over7() {
        GoldmanSachs gs = new GoldmanSachs();
        int[] r = gs.addFractions(new int[] {-1, 5},  new int[] {1, 7});
        assertEquals(-2, r[0]);
        assertEquals(35, r[1]);

        r = gs.addFractions(new int[] {1, -5},  new int[] {1, 7});
        assertEquals(-2, r[0]);
        assertEquals(35, r[1]);
    }

    @Test
    public void should_get5over2_when_given1over2And2() {
        GoldmanSachs gs = new GoldmanSachs();
        int[] r = gs.addFractions(new int[] {2, 1},  new int[] {1, 2});
        assertEquals(5, r[0]);
        assertEquals(2, r[1]);
    }

    @Test(expected = InvalidParameterException.class)
    public void should_raiseException_when_givenZeroInDenominator() {
        GoldmanSachs gs = new GoldmanSachs();
        gs.addFractions(new int[] {-1, 0},  new int[] {-1, 7});
    }
}
