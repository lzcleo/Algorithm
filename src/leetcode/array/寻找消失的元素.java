package leetcode.array;

/**
 * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数
 * 输入：[3,0,1]
 * 输出：2
 */
public class 寻找消失的元素 {
    //直接说一下比较巧妙的做法吧，通过位运算，由于一个数与0异或为本身，与本身异或为0，现在我们的数组索引范围为[0,n-1]，我们补上n，然后与所有索引以及数组中的数字异或，得到的就是所求
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int addIndex = nums.length, res = 0;
        res ^= addIndex;
        for (int i = 0; i < nums.length; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }

    //但是其实还有一种数学的做法，通过等差数列求和公式求出0-n的和，然后减去数组中元素的和，即可得到结果，但是为了确保不会有数组溢出的危险，我们同时加减，与上面的做法一样，加一个索引
    public int missingNumberSecond(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 新补的索引
        res += n - 0;
        // 剩下索引和元素的差加起来
        for (int i = 0; i < n; i++)
            res += i - nums[i];
        return res;

    }
}