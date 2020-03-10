package algorithm;

import algorithms.RabinKarpRollingHash;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RollingHashTest {

    @Test
    public void should_get5_when_RunRabinKarpAlgo() {
        RabinKarpRollingHash rb = new RabinKarpRollingHash("kelvin");
        int r = rb.searchFrom("liangkelvin");
        assertEquals(5, r);
    }

    @Test
    public void should_get0_when_RunRabinKarpAlgo() {
        RabinKarpRollingHash rb = new RabinKarpRollingHash("aa");
        int r = rb.searchFrom("aaa");
        assertEquals(0, r);
    }
}
