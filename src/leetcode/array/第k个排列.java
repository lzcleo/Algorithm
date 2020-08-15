package leetcode.array;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author leolu
 * @create 2020-04-11-18:38
 * https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
 **/
public class 第k个排列 {
    public String getPermutation(int n, int k) {
        //阶乘数组
        int[] factorial = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        return dfs(n, k, new boolean[n], 0, new LinkedList<Integer>(), factorial);
    }

    private String dfs(int n, int k, boolean[] used, int depth, LinkedList<Integer> context, int[] factorial) {
        if (depth == n) {
            String res = "";
            for (Integer i : context) {
                res += "" + i;
            }
            return res;
        }
        int ps = factorial[n - 1 - depth];
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            if (ps < k) {
                k -= ps;
                continue;
            }
            context.offer(i + 1);
            used[i] = true;
            return dfs(n, k, used, depth + 1, context, factorial);
        }
        return "";
    }
}
