package leetcode.array;

import java.util.LinkedList;

/**
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 */
public class 最短无序连续子数组 {
    /**
     * 第一种思路 ：对数组进行排序，进行比对，找到左边界以及右边界
     * 第二种思路 ：使用单调栈找到左边界以及右边界
     */
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int l = nums.length - 1, r = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        //注意往里放的是索引
        for (int i = 0; i < nums.length; i ++) {
            while (!stack.isEmpty() && nums[stack.peekLast()] > nums[i]) {
                l = Math.min(stack.pollLast(), l);
            }
            stack.offerLast(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peekLast()] < nums[i]) {
                r = Math.max(stack.pollLast(), r);
            }
            stack.offerLast(i);
        }
        //注意边界
        return r - l > 0 ? r - l + 1 : 0;
    }
}
