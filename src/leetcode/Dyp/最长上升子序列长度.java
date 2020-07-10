package leetcode.Dyp;


import java.util.Arrays;

/**
 * @author leolu
 * @create 2020-03-22-17:27
 * 动规
 * 定义状态 -> 状态转移方程 -> 初始化 -> 考虑输出 -> 考虑空间压缩
 **/
public class 最长上升子序列长度 {

    /**
     * dp[i] 表示以 nums[i] 结尾的「上升子序列」的长度
     * @param nums
     * @return
     */
    public int lengthOfLISFirst(int[] nums) {
        if (nums == null && nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i ++) {
            int maxValue = 0;
            for (int j = 0; j < i; j ++) {
                if (nums[i] > nums[j])
                    maxValue = Math.max(maxValue, dp[j]);
            }
            dp[i] = maxValue + 1;
        }
        int res = 0;
        for (int i : dp) {
            res = Math.max(res, i);
        }
        return res;
    }

    /**
     * 动规+贪心
     * tail[i] 表示长度为 i + 1 的所有上升子序列的结尾的最小值。
     * @param nums
     * @return
     */
    public int lengthOfLISSecond(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        // tail 数组的定义：长度为 i + 1 的上升子序列的末尾最小是几
        int[] dp = new int[len];
        // 遍历第 1 个数，直接放在有序数组 tail 的开头
        dp[0] = nums[0];
        // end 表示有序数组 tail 的最后一个已经赋值元素的索引
        int end = 0;

        for (int i = 1; i < len; i++) {
            // 【逻辑 1】比 tail 数组实际有效的末尾的那个元素还大
            if (nums[i] > dp[end]) {
                // 直接添加在那个元素的后面，所以 end 先加 1
                end++;
                dp[end] = nums[i];
            } else {
                // 使用二分查找法，在有序数组 tail 中
                // 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
                int left = 0;
                int right = end;
                while (left < right) {
                    // 选左中位数不是偶然，而是有原因的，原因请见 LeetCode 第 35 题题解
                    // int mid = left + (right - left) / 2;
                    int mid = left + ((right - left) >>> 1);
                    if (dp[mid] < nums[i]) {
                        // 中位数肯定不是要找的数，把它写在分支的前面
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                // 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第 1 个大于等于 nums[i] 的元素
                // 因此，无需再单独判断
                dp[left] = nums[i];
            }
            // 调试方法
            // printArray(nums[i], tail);
        }
        // 此时 end 是有序数组 tail 最后一个元素的索引
        // 题目要求返回的是长度，因此 +1 后返回
        return end + 1;
    }


    /**
     *  binarySearch(Object[], int fromIndex, int toIndex, Object key)
     * a：要搜索的数组
     * fromIndex：指定范围的开始处索引（包含）
     * toIndex：指定范围的结束处索引（不包含）
     * key：要搜索的值
     * 如果要搜索的元素key在指定的范围内，则返回搜索值的索引；否则返回-1或“-”（插入点）。
     * 技巧：
     * [1] 该搜索键在范围内，但不是数组元素，由1开始计数，得“ - 插入点索引值”；
     * [2] 该搜索键在范围内，且是数组元素，由0开始计数，得搜索值的索引值；
     * [3] 该搜索键不在范围内，且小于范围（数组）内元素，返回–(fromIndex + 1)；
     * [4] 该搜索键不在范围内，且大于范围（数组）内元素，返回 –(toIndex + 1)。
     * @param nums
     * @return
     * 此时时间复杂度为O(nlogn),空间复杂度为O(n)
     */
    public int lengthOfLISThree(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

}
