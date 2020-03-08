package interviews;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Klook {
    public int findSmallest(int[] A) {
        Set<Integer> s = new TreeSet<>();

        for (int a: A) {
            if (a > 0) s.add(a);
        }

        int i = 1;
        int count = 0;
        while (count < s.size()) {
            if (!s.contains(i)) return i;
            ++i;
            ++count;
        }
        return i;
    }

    private void maxCrossDiscovery(int[] a, int[] b, int[] dp, int[] prev, int cur) {
        for (int i = cur; i < a.length; ++i) {
            for (int j = prev[i]; j < b.length; ++j) {
                if (a[i] == b[j]) {
                    prev[i + 1] = j + 1;
                    ++dp[cur];
                    break;
                }
            }
        }
    }

    public int maxUncrossedLines(int[] A, int[] B) {
        int[] dp = new int[A.length + 1];
        int[] prev = new int[A.length + 1];

        for (int i = 0; i < A.length; ++i) {
            prev[i] = 0;
            maxCrossDiscovery(A, B, dp, prev, i);
        }

        Arrays.sort(dp);
        return dp[A.length];
    }
}
