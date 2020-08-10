package leetcode.array;


import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 */
public class 找到所有数组中消失的数字 {
    //属于会者不难的题
    private List<Integer> res= new LinkedList<>();
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //把所有存在的数字对应的索引里的数变成负数
        for (int i = 0; i < nums.length; i ++) {
            nums[Math.abs(nums[i]) - 1] = -1 * Math.abs(nums[Math.abs(nums[i]) - 1]);
        }
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
