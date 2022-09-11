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
        return Math.min(count, MAX);
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

    @Test
    public void testIntegerPool() {
        Integer a = 130;
        Integer b = Integer.valueOf(130);
        assertFalse(a == b);
    }

    private int compute(int[] coins, int amount, int index) {
        int maxCount = amount / coins[index];
        if (maxCount == 0) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        for (int i = maxCount; i > 0; --i) {
            int remaining = amount - (coins[index] * i);
            if (remaining == 0) {
                min = Math.min(i, min);
                break;
            }

            for (int j = index - 1; j >= 0; --j) {
                int count = compute(coins, remaining, j);
                if (count > -1 && count < Integer.MAX_VALUE) {
                    min = Math.min(min, i + count);
                    break;
                }
            }
        }

        return min;
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;

        Arrays.sort(coins);

        for (int i = coins.length - 1; i >= 0; --i) {
            int count = compute(coins, amount, i);
            if (count > -1 && count < Integer.MAX_VALUE) {
                return count;
            }
        }

        return -1;
    }

    @Test
    public void test() {
        int r = coinChange(new int[]{411,412,413,414,415,416,417,418,419,420,421,422}, 9864);
        assertEquals(24, r);
    }

    private void recursive(List<List<Integer>> rs, List<Integer> r, int n, int k, int target) {
        if (r.size() == n && target != 0) {
            return;
        }

        if (target == 0 && r.size() == n) {
            rs.add(new ArrayList<>(r));
            return;
        }

        for (int i = 1; i <= k; ++i) {
            if (target >= i) {
                r.add(i);
                recursive(rs, r, n, k, target - i);
                r.remove(r.size() - 1);
            } else {
                break;
            }
        }
    }

    public int numRollsToTarget(int n, int k, int target) {
        List<List<Integer>> rs = new ArrayList<>();
        List<Integer> r = new ArrayList<>();
        recursive(rs, r, n, k, target);
        return rs.size() % ((int) Math.pow(10, 9) + 7);
    }

    @Test
    public void test2() {
        int r = numRollsToTarget(30,30, 500);
        assertEquals(222616187, r);
    }
}
