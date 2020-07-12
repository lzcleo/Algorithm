package leetcode.Dyp;

import java.util.Arrays;

/**
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 */
public class 分割等和子集 {
    //f(i,C) 把 i 个数放进容量为C的背包
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum%2 != 0) return false;
        return tryPartiton(nums, nums.length - 1, sum / 2 );
    }

    private boolean tryPartiton(int[] nums, int index, int target) {
        if (target == 0) return true;
        if (target < 0 || index < 0) return false;
        return tryPartiton(nums, index - 1, target) || tryPartiton(nums, index - 1, target - nums[index]);
    }

    //使用DP
    public boolean canPartitionSecond(int[] nums) {
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
