package leetcode.Dyp;

/**
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 */
public class 最长回文子序列 {
    /**
     * 我们在解子序列的题时，通常有三种思路
     * 1 使用一维数组 比如 最长上升子序列长度 dp[i] 通常即为所求
     * 2 使用二维数组 但是题目中有两个序列  比如编辑距离 最长公共子序列 dp[i][j] 表示在子数组或者子序列arr[0,i]与arr[0,j]中我们要求的长度dp[i][j]
     * 3 使用二维数组 但是题目中只有一个序列 比如说最长回文子序列 这个时候  dp[i][j]表示 在子数组 array[i..j] 中，我们要求的子序列(最⻓回文子序列)的⻓度
     */
    //事实证明，如果真的思路清晰，并没有那么多bug，真的一气呵成，写错了=不会
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i ++) {
            dp[i][i] = 1;
        }
        for (int i = len - 2; i >= 0; i --) {
            for (int j = i + 1; j < len; j ++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }
}
