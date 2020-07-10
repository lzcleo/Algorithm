package leetcode.array;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 */
public class 数组中数字出现的次数 {
    /**
     * 时间复杂度 O(N) ： 其中 N位数组 nums 的长度；遍历数组占用 O(N) ，每轮中的常数个位运算操作占用 O(1)O(1) 。
     * 空间复杂度 O(1) ： 数组 res 长度恒为 32 ，占用常数大小的额外空间
     */
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] res = new int[32];
        //统计所有数组中所有数字的二进制表示，并累计加入res数组
        for (int num : nums) {
            for (int j = 0; j < 32; j ++) {
                res[j] += num & 1;
                num >>>= 1;
            }
        }
        int m = 3, result = 0;
        //其他数字均有三个，所以对二进制上每一位对3取余，即得到剩下的数的二进制表示
        for (int i = res.length - 1; i >= 0; i --) {
            result <<= 1;
            result |= res[i] % m;
        }
        return result;
    }
}
