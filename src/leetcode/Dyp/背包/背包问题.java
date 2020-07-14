package leetcode.Dyp.背包;

/**
 * 有一个容量为 size 的背包，要用这个背包装下物品的价值最大，这些物品有两个属性:体积 w 和价值 v。
 *         定义一个二维数组 dp 存储最大价值，其中 dp[i][j] 表示前 i 件物品体积不超过 j 的情况下能达到的最大价值。设第 i 件物品体积为 w，价值为 v，根据第 i 件物品是否添加到背包中，可以分两种情况讨论:
 *         第 i 件物品没添加到背包，总体积不超过 j 的前 i 件物品的最大价值就是总体积不超过 j 的前 i-1 件物品的最大价 值，dp[i][j] = dp[i-1][j]。
 *         第 i 件物品添加到背包中，dp[i][j] = dp[i-1][j-w] + v。
 *         第 i 件物品可添加也可以不添加，取决于哪种情况下最大价值更大
 */
public class 背包问题 {
    //经典01背包问题最原始的解法，易于理解，接下来进行优化
    public int knapsack(int[] weights, int[] values, int size) {
        if (weights.length == 0 || size == 0)
            return 0;
        int n = weights.length;
        int[][] dp = new int[n][size + 1];
        for (int i = 0; i <= size; i ++) {
            dp[0][i] = (i >= weights[0] ? values[i] : 0);
        }
        for (int i = 1; i < n; i ++) {
            for (int j = 0; j <= size; j ++) {
                if (j < weights[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                }
            }
        }
        return dp[n - 1][size];
    }

    //由于每一次我们只依赖上一行，所以我们可以将中间的核心逻辑进行改写，只需要两行
    public int knapsackSecond(int[] weights, int[] values, int size) {
        if (weights.length == 0 || size == 0)
            return 0;
        int n = weights.length;
        int[][] dp = new int[2][size + 1];
        for (int i = 0; i <= size; i ++) {
            dp[0][i] = (i >= weights[0] ? values[i] : 0);
        }
        for (int i = 1; i < n; i ++) {
            for (int j = 0; j <= size; j ++) {
                if (j < weights[i % 2]) {
                    dp[i % 2][j] = dp[(i - 1) % 2][j];
                } else {
                    dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[(i - 1) % 2][j - weights[i]] + values[i]);
                }
            }
        }
        return dp[(n - 1) % 2][size];
    }

    //我们在计算dp[i][j]时，我们主要依赖dp[i - 1][j]和dp[i - 1][j - weights[i]]，我们可以尝试用一行解决
    public int knapsackThird(int[] weights, int[] values, int size) {
        if (weights.length == 0 || size == 0)
            return 0;
        int n = weights.length;
        int[] dp = new int[size + 1];
        for (int i = 0; i <= size; i ++) {
            dp[i] = (i >= weights[0] ? values[i] : 0);
        }
        for (int i = 1; i < n; i ++) {
            for (int j = size; j >= weights[i]; j --) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }
        return dp[size];
    }
}
