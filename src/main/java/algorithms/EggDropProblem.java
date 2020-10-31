package algorithms;

/**
 *   Given n-egg and k-floor
 */
public class EggDropProblem {
    private int eggDrop(int n, int k) {
        if (n == 1 || k <= 1) return k;

        int drop, min = Integer.MAX_VALUE;
        for (int i = 1; i <= k; ++i) {
            drop = Math.max(eggDrop(n - 1, i - 1), eggDrop(n, k - i));
            min = Math.min(drop, min);
        }

        return min + 1;
    }

    public int recursiveSolution(int n, int k) {
        return eggDrop(n, k);
    }

    public int dpSolution(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= k; ++i) dp[0][i] = 0;
        for (int i = 0; i <= n; ++i) dp[i][0] = 0;
        for (int i = 1; i <= k; ++i) dp[1][i] = i;
        for (int i = 1; i <= n; ++i) dp[i][1] = 1;

        int drop;
        for (int i = 2; i <= n; ++i) {
            for (int j = 2; j <= k; ++j) {
                int min = Integer.MAX_VALUE;
                for (int m = 1; m <= j; ++m) {
                    drop = Math.max(dp[i - 1][m - 1], dp[i][j - m]) + 1;
                    min = Math.min(drop, min);
                }
                dp[i][j] = min;
            }
        }

        return dp[n][k];
    }
}
