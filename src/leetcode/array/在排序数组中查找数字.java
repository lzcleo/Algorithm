package leetcode.array;

public class 在排序数组中查找数字 {
    /**
     * 统计一个数字在排序数组中出现的次数
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     */
    public int search(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }

    private int helper(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = ((r - l) >> 1) + l;
            if (nums[m] <= target) {
                l = m + 1;
            } else {
                r = m -1;
            }
        }
        return r;
    }

    /**
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字
     * 我们统计
     */
    public int singleNumber(int[] nums) {
        int[] counts = new int[32];
        //我们将所有数字转化成二进制之后，对应位上进行汇总，由于其他数字都出现了三次，所以我们对3取余，剩下的即为唯一数字的二进制表示，我们将其复原就好
        for(int num : nums) {
            for(int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }
        //其实只要对这里的m进行改动，可以解决通用的问题
        int res = 0, m = 3;
        for(int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % m;
        }
        return res;
    }

}
