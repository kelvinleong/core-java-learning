package math;

import maths.GreatestCommonDivisor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreatestCommonDivisorTest {
    @Test
    public void Should_Get3_When_Given9And6() {
        GreatestCommonDivisor gcd = new GreatestCommonDivisor();
        int result = gcd.educlid(9, 6);
        assertEquals(3, result);

        result = gcd.binarySearch(9, 6);
        assertEquals(3, result);
    }

    @Test
    public void Should_Get2_When_Given2And4() {
        GreatestCommonDivisor gcd = new GreatestCommonDivisor();
        int result = gcd.educlid(4, 2);
        assertEquals(2, result);

        result = gcd.binarySearch(4, 2);
        assertEquals(2, result);
    }

    @Test
    public void Should_Get2_When_Given2And2() {
        GreatestCommonDivisor gcd = new GreatestCommonDivisor();
        int result = gcd.educlid(4, 2);
        assertEquals(2, result);

        result = gcd.binarySearch(4, 2);
        assertEquals(2, result);
    }
}
