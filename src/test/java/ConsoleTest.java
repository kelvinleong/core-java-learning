


import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class ConsoleTest {
    final int SCALE = 1000000;
    final int MAX = 1000000000;
    public int solution(int[] A, int[] B) {
        // write your code in Java SE 8
        double[] C = new double[A.length];
        for (int i = 0; i < A.length; ++i) {
            C[i] = (double) (A[i] + (B[i] / SCALE));
        }

        int count = 0;
        for (int i = 0; i < C.length; ++i) {
            for (int j = i; j < C.length; ++j) {
                if (C[j] * C[i] >= C[i] + C[j]) {
                    ++count;
                }
            }
        }
        return count > MAX ? MAX : count;
    }


    public void wiggleSort(int[] nums) {
        final int SCALE = 1000000;
        final int MAX = 1000000000;
        List<String> a = new ArrayList<String>();
        Map<String, String> m = new HashMap<>();
        m.get("a");
    }

    private class Obj {
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    @Test
    public void testOptional() {
        Obj o = new Obj();
        o.setFirstName("kelvin");
        var c = Optional.ofNullable(o.getLastName()).map(Optional::of);
                //.or(Optional::empty);

        assertFalse(c.isPresent());

        o = null;
        var c1 = Optional.ofNullable(o)
                .map(Obj::getFirstName)
                .or(() -> Optional.of("default"))
                .get();
        assertEquals("default", c1);

        o = new Obj();
        o.setFirstName("kelvin");
        o.setLastName("liang");

        var c2 = Optional.ofNullable(o).map(obj -> obj.getFirstName() + obj.getLastName());
    }

    private Optional<String> create(String s) {
        return Optional.ofNullable(s)
                .map(o -> "last name is: " + o);
                //.or(Optional::empty);
    }

    @Test
    public void testChainOptionals() {
        String a = null;
        var r = create(a);
        assertTrue(r.isEmpty());
    }
}
