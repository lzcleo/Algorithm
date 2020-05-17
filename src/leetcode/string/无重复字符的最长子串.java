package leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author leolu
 * @create 2020-04-12-12:03
 * 第一想法毫无疑问是检查所有可能的区间，同时每一个区间是否有重复的字符，第一步检查所有的区间时间复杂度为O(n^2),检查是否有重复的字符，
 * 需要再次遍历整个区间，时间复杂度O(n),则时间复杂度为O(n^3)
 * 但是如果区间[i,j)已经被证明没有重复字符，只需要判断j位置字符是否在其中就可，此时时间复杂度为O(n^2)
 * 但是我们可以通过使用HashSet降低判断是否在其中的时间复杂度，下面直接写这种做法
 **/
public class 无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null && s.length() == 0) return 0;
        int len = s.length();
        HashSet<Character> set = new HashSet<>();
        int i = 0, j = 0,res = 0;
        while (i < len && j < len) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j ++));
//                res = Math.max(res, j - i + 1);  边界条件写错 由于上一步已经 j ++ 所以直接j - i即为子串长度
                res = Math.max(res, j - i);
            } else {
                set.remove(s.charAt(i ++));
            }
        }
        return res;
    }

    /**
     * 来自题解
     * @param s
     * @return
     *
     */
    public  int lengthOfLongestSubstringSecond(String s) {
        int n = s.length(), ans = 0;
        //创建map窗口,i为左区间，j为右区间，右边界移动
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            // 如果窗口中包含当前字符，
            if (map.containsKey(s.charAt(j))) {
                //左边界移动到 相同字符的下一个位置和i当前位置中更靠右的位置，这样是为了防止i向左移动
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //比对当前无重复字段长度和储存的长度，选最大值并替换
            //j-i+1是因为此时i,j索引仍处于不重复的位置，j还没有向后移动，取的[i,j]长度
            ans = Math.max(ans, j - i + 1);
            // 将当前字符为key，下一个索引为value放入map中
            // value为j+1是为了当出现重复字符时，i直接跳到上个相同字符的下一个位置，if中取值就不用+1了
            map.put(s.charAt(j), j+1);
        }
        return ans;
    }
}
