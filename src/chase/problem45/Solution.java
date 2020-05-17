package chase.problem45;

import java.util.Arrays;

/**
 * @author leolu
 * @create 2020-02-05-19:35
 * 把数组排成最小的数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数打印能拼接出的所有数字中最小的一个。
 * 例如输入数组 {3，32，321}，则打印出这三个数字能排成的最小数字为 321323。
 **/
public class Solution {
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            return "";
        int n = numbers.length;
        String[] nums = new String[n];
        for (int i = 0; i < n; i++)
            nums[i] = numbers[i] + "";
        Arrays.sort(nums, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        String ret = "";
        for (String str : nums)
            ret += str;
        return ret;
    }
}
