package leetcode.Dyp;

import java.util.ArrayList;

/**
 * @author leolu
 * @create 2020-04-12-13:17
 * 这道题其实一看就可以看出来是用dp，但是我竟然不知道怎么处理数字..
 **/
public class 数字翻译成字符串 {

    public static int MytranslateNum(int num) {
        char[] input = String.valueOf(num).toCharArray();
        int len = input.length;
        int[] dp = new int[len + 1];
        dp[0] = 1; dp[1] = 1;
        for (int i = 1; i < len; i ++) {
            int j = (input[i - 1] - '0') * 10 + (input[i] - '0');
            if (j <= 25 && j >= 10) {
                dp[i + 1] = dp[i] + dp[i - 1];
            } else {
                dp[i + 1] = dp[i];
            }
        }
        return dp[len];
    }

    public int translateNumFirst(int num) {
        char[] sc = String.valueOf(num).toCharArray();
        int n = sc.length;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            //if (sc[i - 1] >= '0' && sc[i - 1] <= '9') {
            f[i] += f[i - 1];
            //}
            if (i > 1) {
                int a = (sc[i - 2] - '0') * 10 + (sc[i - 1] - '0');
                if (a >= 10 && a <= 25) {
                    f[i] += f[i - 2];
                }
            }
        }
        return f[n];
    }

    /**
     * 题解处看到的 给大佬跪了
     *
     * 这道题其实就是一个递归：递归出口是num是只有一位数，以xyzcba为例，先取最后两位（个位和十位）即ba，如果ba>=26，
     * 必然不能分解成f(xyzcb)+f(xyzc)，此时只能分解成f(xyzcb);但还有一种情况，就是ba<=9,也就是该数十位上为0，此时也不能分解。
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        if (num <= 9) {
            return 1;
        }
        //xyzcba
        int ba = num % 100;
        if (ba <= 9 || ba >= 26) {
            return translateNum(num / 10);
        } else {
            return translateNum(num / 10) + translateNum(num / 100);
        }

    }

}

















