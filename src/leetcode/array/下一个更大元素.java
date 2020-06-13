package leetcode.array;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * User: leo
 * Date: 2020/6/10
 * Time: 23:03
 */
public class 下一个更大元素 {
    /**
     * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出: [-1,3,-1]
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElementFirst(int[] nums1, int[] nums2) {
         if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || nums1.length > nums2.length) return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i ++) {
            map.put(nums2[i], i);
        }
        int[] memo = nextGreaterElementFirst(nums2);
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i ++) {
            res[i] = memo[map.get(nums1[i])];
        }
        return res;

    }

    //单调栈的常规模板
    /**
     *
     * @param nums2
     * @return
     */
    private int[] nextGreaterElementFirst(int[] nums2) {
        int[] res = new int[nums2.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = nums2.length - 1; i >= 0; i --) {
            while(!stack.isEmpty() && stack.getFirst() <= nums2[i]) {
                stack.removeFirst();
            }
            res[i] = stack.isEmpty() ? -1 : stack.getFirst();
            stack.addFirst(nums2[i]);
        }
        return res;
    }

}
