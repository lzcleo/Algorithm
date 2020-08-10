package leetcode.Dyp;

import java.util.Arrays;
import java.util.HashMap;

/**
 * User: leo
 * Date: 2020/6/20
 * Time: 19:41
 */
public class 打家劫舍 {
    /**
     *你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * 下面根据传参分为三种，规则相同
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            if (i == 1) {
                dp[i] = Math.max(dp[0], nums[i]);
            } else {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
        }
        return dp[nums.length - 1];
    }

    //优化空间复杂度
    public int robSecond(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

}

class 打家劫舍II {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }
    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }
}

class 打家劫舍III {
    /**
     * 首先是使用递归，对于每个节点，都会有两种选择，偷这个节点以及不偷这个节点，比较好理解，但是要注意的是避免空指针异常
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }
        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }
        return Math.max(money, rob(root.left) + rob(root.right));
    }
    /**
     * 使用记忆化提升效率
     */
    private HashMap<TreeNode, Integer> memo = new HashMap<>();
    public int robSecond(TreeNode root) {
        if (root == null) return 0;

        if (memo.containsKey(root)) return memo.get(root);

        int money = root.val;
        if (root.left != null) {
            money += (robSecond(root.left.left) + robSecond(root.left.right));
        }
        if (root.right != null) {
            money += (robSecond(root.right.left) + robSecond(root.right.right));
        }
        int res = Math.max(money, robSecond(root.left) + robSecond(root.right));

        memo.put(root, res);

        return res;
    }
    /**
     * 使用动态规划
     */
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
