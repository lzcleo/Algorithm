package leetcode.Dyp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author leolu
 * @create 2020-04-03-21:33
 **/
public class N个骰子的点数 {
    public double[] twoSumFirst(int n) {
        if (n <= 0)
            return null;
        int part = 6;
        int total = part * n;
//        int[][] dp = new int[part + 1][total + 1]; 这里写错了
        int[][] dp = new int[n + 1][total + 1];
        for (int m = 1; m <= 6; m ++) {
            dp[1][m] = 1;
        }
        for (int i = 2; i <= n; i ++) {
            for (int j = i; j <= part * i; j ++) {
                for (int k = 1; k <= part && k < j; k ++) {
//                    dp[i][j] += dp[i - 1][part * i - k];
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        double sum = Math.pow(part, n);
//        double[] res = new double[total - n];  这个地方边界又弄错了
        double[] res = new double[total - n + 1];
        for (int i = n; i <= total; i ++) {
            res[i - n] = dp[n][i] / sum;
        }
        return res;
    }

    public double[] twoSumSecond(int n) {
        if (n <= 0)
            return null;
        int part = 6;
        int total = part * n;
        long[][] dp = new long[2][total + 1];
        for (int k = 1; k <= 6; k ++) {
            dp[0][k] = 1;
        }
        int flag = 1;
        for (int i = 2; i <= n; i ++) {
            for (int j = 1; j <= total; j ++) {
                dp[flag][j] = 0;
            }
            for (int j = i; j <= i * part; j ++) {
                for (int k = 1; k <= part && k < j; k ++) {
                    dp[flag][j] += dp[1 - flag][j - k];
                }
            }
            flag = 1 - flag;
        }
        double sum = Math.pow(part, n);
        double[] res = new double[total - n + 1];
        for (int i = n ; i <= total; i ++) {
            res[i - n] = dp[1 - flag][i] / sum;
        }
        return res;
    }

    @Test
    public void test() {
        double[] doubles = twoSumSecond(2);
        for (double d : doubles) {
            System.out.println(d);
        }
    }


}
