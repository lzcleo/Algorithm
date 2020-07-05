package leetcode.Dyp;

import java.util.HashMap;
import java.util.Map;

//请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
// 示例 1:
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
public class 最长不含重复字符的子字符串 {
    //DP 状态为以j结尾的字符最长不含重复字符的长度
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0;
        for(int j = 0; j < s.length(); j++) {
            int i = dic.getOrDefault(s.charAt(j), -1); // 获取索引 i
            dic.put(s.charAt(j), j); // 更新哈希表
            tmp = tmp < j - i ? tmp + 1 : j - i; // dp[j - 1] -> dp[j]
            res = Math.max(res, tmp); // max(dp[j - 1], dp[j])
        }
        return res;
    }

    //以滑动窗口的思想优化，不断更新左边界
    private HashMap<Character, Integer> index = new HashMap<>();
    private int res;
    public int lengthOfLongestSubstringSecond(String s) {
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
