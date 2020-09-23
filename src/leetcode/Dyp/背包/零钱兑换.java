package leetcode.Dyp.背包;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 零钱兑换 {
    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     */
    /**
     * f(x)就设为凑出n的最小硬币数量,第一种暴力解法，超时
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        return getCoinChange(coins, amount);
    }

    private int getCoinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = getCoinChange(coins, amount - coin);
            if (sub == -1) continue;
            res = Math.min(sub + 1, res);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 记忆化搜索优化
     */
    private int[] memo;
    public int coinChangeSecond(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        memo = new int[amount + 1];
        return getCoinChangeSecond(coins, amount);
    }

    private int getCoinChangeSecond(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (memo[amount] != 0) return memo[amount];
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = getCoinChangeSecond(coins, amount - coin);
            if (sub == -1) continue;
            res = Math.min(sub + 1, res);
        }
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }

    //自底向上求解
    //完全背包问题， 背包大小放外面
    public int coinChangeThird(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1];
        //这里有一个小细节，初始化为amount + 1，也就相当于Integer.MAX_VALUE
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i ++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }


    /**
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
     * 输入: amount = 5, coins = [1, 2, 5]
     * 输出: 4
     * 解释: 有四种方式可以凑成总金额:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     */
    /**
     * 完全背包问题
     * 这里dp[i][j]表示用i个硬币，可以凑成j的组合数
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        // base case
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++)
                if (j - coins[i-1] >= 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i-1]];
                else
                    dp[i][j] = dp[i - 1][j];
        }
        return dp[n][amount];
    }

    //做这种问题的时候特别注意一点，就是边界条件
    // 上面的做法中i从1开始，所以第i个硬币的价值应该为coins[i-1]
    // 这里我们的i从0开始，所以第i个硬币的价值即为coins[i]
    public int changeSecond(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        // base case
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= amount; j++)
                if (j - coins[i] >= 0) dp[j] += dp[j - coins[i]];
        }
        return dp[amount];
    }

}
