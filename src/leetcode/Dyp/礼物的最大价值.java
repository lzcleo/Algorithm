package leetcode.Dyp;

/**
 * @author leolu
 * @create 2020-04-11-11:13
 **/
public class 礼物的最大价值 {
    /**
     * 直接写的空间复杂度为O（n）的版本 ，有一说一 写的真的丑陋， 附上CYC大佬的代码
     * @param grid
     * @return
     */
    //        public int maxValue(int[][] grid) {
//            if (grid == null || grid.length == 0 || grid[0].length == 0 ) return 0;
//            int m = grid.length;
//            int n = grid[0].length;
//            int[] dp = new int[n];
//            for (int i = 0; i < n; i ++) {
//                dp[i] = grid[0][1];  这里怎么会是直接赋值呢
//            }
//            for (int i = 1; i < m; i ++) {
//                for (int j = 0; j < n; j ++) {
//                    if (j == 0) {
//                        dp[j] += grid[i][j];
//                    } else {
//                        dp[j] = Math.max(dp[j - 1], dp[j]) + grid[i][j];
//                    }
//                }
//            }
//            return dp[m - 1][j - 1];这个地方为什么要返回这个..明明是一维数组
//        }
    public int maxValueFirst(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0 ) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i ++) {
            if (i == 0) {
                dp[i] = grid[0][0];
            } else {
                dp[i] = dp[i -1] + grid[0][i];
            }

        }
        for (int i = 1; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (j == 0) {
                    dp[j] += grid[i][j];
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }
    public int maxValueSecond(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0)
            return 0;
        int n = values[0].length;
        int[] dp = new int[n];
        for (int[] value : values) {
            dp[0] += value[0];
            for (int i = 1; i < n; i++)
                dp[i] = Math.max(dp[i], dp[i - 1]) + value[i];
        }
        return dp[n - 1];
    }
    /**
     * 其实还可以缩小空间复杂度
     * 空间复杂度 O(1) ： 原地修改使用常数大小的额外空间
     */
    public int maxValueThird(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) continue;
                if(i == 0) grid[i][j] += grid[i][j - 1] ;
                else if(j == 0) grid[i][j] += grid[i - 1][j];
                else grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[m - 1][n - 1];
    }
}
