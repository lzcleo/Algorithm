package leetcode.Other;

//编写一个程序，找出第 n 个丑数。
// 丑数就是质因数只包含 2, 3, 5 的正整数。
public class 丑数 {
    //为什么下面这三个地方不能写continue呢，因为有可能存在数是235中多个数的公倍数，比如说6，这样就会出错
    public static int nthUglyNumber(int n) {
        if (n <= 0) return 0;
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i ++) {
            int withTwo = dp[a] * 2;
            int withThree = dp[b] * 3;
            int withFive = dp[c] * 5;
            dp[i] = Math.min(Math.min(withTwo, withThree), withFive);
            if (dp[i] == withTwo) {
                a ++;
//                 continue;
            }
            if (dp[i] == withThree) {
                b ++;
//                 continue;
            }
            if (dp[i] == withFive) {
                c ++;
//                 continue;
            }
        }
        return dp[n-1];
    }
}
