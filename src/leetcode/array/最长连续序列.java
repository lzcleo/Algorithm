package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * User: leo
 * Date: 2020/6/6
 * Time: 19:23
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class 最长连续序列 {
    public static void main(String[] args) {
        int[] ints = {100,4,200,1,3,2};
        System.out.println(longestConsecutive(ints));
    }
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashSet<Integer> memo = new HashSet<>();
        for (int num : nums) {
            memo.add(num);
        }
        int max = 0;
        for (int num : nums) {
            if (!memo.contains(num - 1)) {
                int curNum = num;
                int cur = 1;
                while (memo.contains(curNum + 1)) {
                    curNum ++;
                    cur ++;
                }
//                这里如果这样写是不对的
//                while (memo.contains(curNum ++)) {
//                    cur ++;
//                }
                max = Math.max(cur, max);
            }
        }
        return max;
    }
    /**
     * 这是第一次自己写的错误解法，这是过不了这类型的用例的
     * 输入 [1,2,0,1]
     * 输出 2
     * 正确输出 3
     * @param nums
     * @return
     */
    public int longestConsecutivewrong(int[] nums) {
        //太久没有写算法了，连第一步检查边界条件都已经忘了
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int cur = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] - nums[i - 1] == 1) {
                cur ++;
            } else {
                cur = 1;
            }
            if (cur > max) {
                max = cur;
            }
        }
        return max;
    }
}
