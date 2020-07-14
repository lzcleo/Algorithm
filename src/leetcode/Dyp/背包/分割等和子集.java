package leetcode.Dyp.背包;

import java.util.Arrays;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 */
public class 分割等和子集 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum%2 != 0) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        Arrays.fill(dp, false);
        for (int i = 0; i <= target; i ++) {
            dp[i] = (nums[0] == i);
        }
        for (int i = 1; i < nums.length; i ++) {
            for (int j = target; j >= nums[i]; j --) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
