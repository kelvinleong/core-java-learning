package leetcodeChallenge;

/**
 *
 *  Refer to this leetcode challenge:
 *  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 */
public class BestTimeBuySellStock {

    /**
     *  Some declarations: the pre-requisite is that you can only buy before you sell (in you hold any stock) and given
     *  that you have n prices (i.e. within n days). So you can only buy and sell at most k/2 times respectively i.e.,
     *  you buy at the first day and sell the other day so on and so forth. So if given a k > n / 2, you may consider a
     *  trivial way to accumulate the max profit when price in some date is higher than its previous.
     *  But when k is smaller than n / 2 which means that, that will be a bit tricky. It's a very interesting question
     *  which is similar to Knap-sack problem. Let's try it in this way, at k-th day, one might either do nothing which
     *  means you either hold the stock or hold nothing, or one sell the stock on hold which generate a bit profit in
     *  its pnl which I call it out and in strategy. This can be illustrated in such a way:
     *
     *  {maxPnl = max(on yesterday's pnl, sell stock hold days before + pnl before the last time you buy the stock)}
     *
     *  well, let's translate it into pseudo codes and say we have a dp[k][i] to describe the max profit at the k-th
     *  time you are buying at i-th day.
     *
     *  for i in 1...k
     *      for j in 1...n
     *          dp[i][j] = max(dp[i][j - 1], p[j - 1] - p[jj] + dp[i - 1][jj]) where jj in 1...j
     *
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length, delta;

        if (k > n /2) {
            int maxPnl = 0;
            for (int i = 1; i < n; ++i) {
                if (prices[i] - prices[i -1] > 0) maxPnl += (prices[i] - prices[i -1]);
            }
            return maxPnl;
        }

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i  < k; ++i) {
            delta = dp[i - 1][0] - prices[0];
            for (int j = 1; j < n; ++j) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + delta);
                delta = Math.max(delta, prices[j] - dp[i - 1][j]);
            }
        }

        return dp[k][n - 1];
    }
}
