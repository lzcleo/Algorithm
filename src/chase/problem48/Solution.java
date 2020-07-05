package chase.problem48;

import java.util.HashMap;

/**
 * @author leolu
 * @create 2019-11-19-21:46
 *
 * 最长不含重复字符的子字符串
 *
 * 蛮力法的效率为O（n3）
 *
 * 我们定义函数f（i）表示以第i个字符为结尾的不包含重复字符的子字符串的最长长度
 *
 *
 *
 **/
public class Solution {
    private HashMap<Character, Integer> index = new HashMap<>();
    private int res;
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] input = s.toCharArray();
        int j = -1;
        for (int i = 0; i < input.length; i ++) {
            j = Math.max(index.getOrDefault(input[i], -1), j);
            index.put(input[i], i);
            res = Math.max(res, i - j);
        }
        return res;
    }
}
