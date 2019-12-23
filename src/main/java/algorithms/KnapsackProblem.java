package algorithms;

public class KnapsackProblem {

    /**
     *   This is the recursive way to solve the knapsack problem
     *   Noted, you either take the whole or none of the item (i.e. faction is not allowed)
     *   Therefore, for each item it only has two status either contained or not
     *   If the item is over weighted, pass the current available weight to the next item and try
     *   If the item is contained, subjecting its weight from available weight
     *   If not, pass the current available weight to the next item and try
     *   This algorithm has a time complexity of O(2^n)
     */
    public int knapsack(int[] values, int[] weights, int w, int i) {
        if (i < 0 || w == 0) {
            return 0;
        }

        if (weights[i] > w) {
            return knapsack(values, weights, w, i - 1);
        } else {
            return Math.max(values[i] + knapsack(values, weights, w - weights[i], i - 1),
                    knapsack(values, weights, w, i - 1));
        }
    }

    /**
     *   This is the dynamic programming way to solve the knapsack problem
     *   This algorithm has a time complexity of O(n^2)
     */
    public int knapsackUsingDp(int[] values, int[] weights, int w) {
        int[][] dp = new int[values.length + 1][w + 1];

        for (int i = 0; i <= values.length; ++i) {
            for (int j = 0; j <= w; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (weights[i -1] <= j) {
                    dp[i][j] = Math.max(values[i - 1] + dp[i -1][j - weights[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[values.length][w];
    }

}
