package leetcode.Dyp;

/**
 * 做过的简单题，所以上来直接写的优化空间版，需要注意的是，有一个地方写错了，而且代码不太简洁
 */
public class 最小路径和 {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
//        一开始初始化是这样做的，是不对的，值得注意
//        for (int i = 0; i < n; i ++) {
//            dp[i] = grid[0][i];
//        }
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i] = grid[0][i];
            } else {
                dp[i] = grid[0][i] + dp[i - 1];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[j] += grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }

    //另一种写法
    public int minPathSumSecond(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[j] = dp[j];
                } else if (i == 0) {
                    // 只能从上侧走到该位置
                    // 只能从左侧走到该位置 dp[j] = Math.min(dp[j - 1], dp[j]);
                    dp[j] = dp[j - 1];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]);
                }
                dp[j] += grid[i][j];
            }
        }
        return dp[n - 1];
    }
}
