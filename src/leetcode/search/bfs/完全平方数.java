package leetcode.search.bfs;


import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author leolu
 * @create 2020-04-07-15:19
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 **/
public class 完全平方数 {
    /**
     * 这种解法的思路是使用BFS，第一次想把所有比n小的完全平方数放入一个集合内方便存取，但是其实并没有必要，而且往下层求解
     * 的时候可能包含的完全平方数应该是越来越少的
     */
    public int numSquaresFirst(int n) {
        HashSet<Integer> set = new HashSet<>();
//        ArrayList<Integer> memo = new ArrayList<>();
//        for (int i = 1; i <= Math.sqrt(n); i ++) {
//            if (i * i <= n)
//                memo.add(i);
//        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        int level = 0;
        while (!queue.isEmpty()) {
            level ++;
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                Integer pollNum = queue.poll();
                for (int j = 1; j * j <= pollNum; j ++) {
                    int next = pollNum - j * j;
                    if (next == 0) {
                        return level;
                    }
                    if (!set.contains(next)) {
                        set.add(next);
                        queue.offer(next);
                    }
                }
            }
        }
        return 0;
//        return level;
    }
    /**
     * 动态转移方程为：dp[i] = MIN(dp[i], dp[i - j * j] + 1)，i表示当前数字，j*j表示平方数
     * 时间复杂度：O(n*sqrt(n))
     * @param n
     * @return
     */
    public int numSquaresSecond(int n) {
        int[] dp = new int[n + 1]; // 默认初始化值都为0
        for (int i = 1; i <= n; i++) {
            dp[i] = i; // 最坏的情况就是每次+1
            for (int j = 1; i - j * j >= 0; j++) {
                //这里需要注意的是我们需要遍历所有此时比i小的完全平方数，而不会每次取最大的完全平方数，如果每次取最大
                // 转化为贪心算法，这一题易知贪心算法并不正确
                // 动态转移方程
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    @Test
    public void test() {

    }
}
