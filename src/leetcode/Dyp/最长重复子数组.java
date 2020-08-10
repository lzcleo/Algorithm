package leetcode.Dyp;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author leolu
 * @create 2020-07-02-8:13
 **/
public class 最长重复子数组 {
    //dp解法较好理解，时间复杂度为O(N×M)，空间复杂度为O(N×M)，空间复杂度可通过滚动数组优化到O(min(N,M))
    public int findLength(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    /**
     * 滑动窗口
     * 我们可以枚举 A 和 B 所有的对齐方式。
     * 对齐的方式有两类：
     * 第一类为 A 不变，B 的首元素与 A 中的某个元素对齐；
     * 第二类为 B 不变，A 的首元素与 B 中的某个元素对齐。
     * 对于每一种对齐方式，我们计算它们相对位置相同的重复子数组即可
     * 时间复杂度为O((N+M)×min(N,M))，空间复杂度为O(1)
     */
    public int findLengthSecond(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxlen = maxLength(A, B, i, 0, len);
            ret = Math.max(ret, maxlen);
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxlen = maxLength(A, B, 0, i, len);
            ret = Math.max(ret, maxlen);
        }
        return ret;
    }

    public int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }
}
