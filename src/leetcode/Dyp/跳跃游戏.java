package leetcode.Dyp;

import java.util.Arrays;

public class 跳跃游戏 {
    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个位置。
     *
     * 输入: [2,3,1,1,4]
     * 输出: true
     * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
     */
    //问题转化成能跳的最大距离能否超过数组的长度
    public boolean canJump(int[] nums) {
        int longest = 0;
        for (int i = 0; i < nums.length - 1; i ++) {
            longest = Math.max(longest, i + nums[i]);
            if (longest <= i) {
                return false;
            }
        }
        return longest >= nums.length - 1;
    }

    /**
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     */
    //下面是自顶向下的记忆化搜索，时间复杂度为递归深度*每次递归的时间复杂度，为O(N^2)
    private int[] memo;
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        memo = new int[nums.length];
        Arrays.fill(memo, memo.length);
        return jump(nums, 0);
    }

    private int jump(int[] nums, int index) {
        if (index >= nums.length - 1) return 0;
        if (memo[index] != memo.length) return memo[index];
        int len = nums[index];
        for (int i = 1; i <= len; i ++) {
            memo[index] = Math.min(jump(nums, index + i) + 1, memo[index]);
        }
        return memo[index];
    }

    //使用贪心算法进行优化,i 和 end 标记了可以选择的跳跃步数，farthest 标记了所有选择 [i..end] 中能够跳到的最远距离，jumps 记录了跳跃次数
    //举个例子吧[3,1,4,2,3,4,6],我们在走第一步时，我们可以走到三个位置，1，4，2，很明显，我们选4的时候能够获得最远距离，这就是贪心的思想，选"最有潜力"的那个
    public int jumpSecond(int[] nums) {
        int n = nums.length;
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(nums[i] + i, farthest);
            if (end == i) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }
}
