package leetcode.Dyp;

/**
 * @author leolu
 * @create 2020-04-09-23:26
 **/
public class 买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int max_k = 2;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int m = max_k; m >= 1; m--) {
                if (i - 1 == -1) {
                    dp[i][m][0] = 0;
                    // 解释：
                    //   dp[i][0]
                    // = max(dp[-1][0], dp[-1][1] + prices[i])
                    // = max(0, -infinity + prices[i]) = 0
                    dp[i][m][1] = -prices[i];
                    //解释：
                    //   dp[i][1]
                    // = max(dp[-1][1], dp[-1][0] - prices[i])
                    // = max(-infinity, 0 - prices[i])
                    // = -prices[i]
                    continue;
                }
                dp[i][m][0] = Math.max(dp[i-1][m][0], dp[i-1][m][1] + prices[i]);
                dp[i][m][1] = Math.max(dp[i-1][m][1], dp[i-1][m-1][0] - prices[i]);
            }
        }
// 穷举了 n × max_k × 2 个状态，正确。
        return dp[n - 1][max_k][0];
    }
}
