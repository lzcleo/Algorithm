package leetcode.Other;

import java.util.Arrays;

public class 把数组排成最小的数 {
    public static String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i ++) {
            str[i] = nums[i] + "";
        }
        Arrays.sort(str, (s1, s2) ->(s1 + s2).compareTo(s2 + s1));
        String res = "";
        for (String s : str) {
            res += s;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 12, 23, 34};
        String s = minNumber(nums);
        System.out.println(s);
    }

}
