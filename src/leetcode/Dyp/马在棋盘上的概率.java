package leetcode.Dyp;

//leetcode688
public class 马在棋盘上的概率 {
    double knightProbability(int N, int K, int r, int c) {
        if (N == 0) {
            return 0;
        }
        //这里解释一下为什么要设为N+4，因为我们最后需要跳到(r,c)位置，我们要枚举最后一步之前所有可能的位置，也就是需要把走到棋盘外的可能同时进行枚举，我们新建的这个dp数组其实[2,N+1]对应我们原来的数组索引[0,N-1]
        double[][][] dp = new double[N + 4][N + 4][K + 1];
        for (int i = 0; i < N + 4; i++) {
            for (int j = 0; j < N + 4; j++) {
                if ((i) >= 2 && (i) <= N + 1) {
                    if ((j) >= 2 && (j) <= N + 1) {
                        dp[i][j][0] = 1;
                    }
                } else {
                    dp[i][j][0] = 0;
                }
            }
        }
        for (int k = 1; k <= K; k++) {
            for (int i = 2; i <= N + 1; i++) {
                for (int j = 2; j <= N + 1; j++) {
                    dp[i][j][k] = (dp[i - 2][j - 1][k - 1] + dp[i - 2][j + 1][k - 1] +
                            dp[i - 1][j - 2][k - 1] + dp[i - 1][j + 2][k - 1] +
                            dp[i + 1][j - 2][k - 1] + dp[i + 1][j + 2][k - 1] +
                            dp[i + 2][j - 1][k - 1] + dp[i + 2][j + 1][k - 1]) / 8.0;
                }
            }
        }

        return dp[r + 2][c + 2][K];
    }
}
