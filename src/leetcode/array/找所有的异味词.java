package leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class 找所有的异味词 {
    private List<Integer> result = new ArrayList<>();
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null || s.length() == 0 || p.length() == 0 || p.length() > s.length()) return result;
        int left = 0, right = 0, count = 0;
        HashMap<Character, Integer> cur = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        for (Character c : p.toCharArray()) {
            need.merge(c, 1, (a, b) -> a + b);
        }
        while (right < s.length()) {
            char c = s.charAt(right);
            if (need.containsKey(c)) {
                cur.merge(c, 1, (a, b) -> a + b);
                if (need.get(c) == cur.get(c)) {
                    count ++;
                }
            }
            right ++;
            while (right - left >= p.length()) {
                if (count == need.size()) {
                    result.add(left);
                }
                char d = s.charAt(left);
                if (need.containsKey(d)) {
                    if (need.get(d) == cur.get(d)) {
                        count --;
                    }
                    cur.merge(d, 1, (a, b) -> a - b);
                }
                left ++;
            }
        }
        return result;
    }

    @Test
    public void test() {
        List<Integer> anagrams = findAnagrams("cbaebabacd", "abc");
        System.out.println(anagrams);
    }
}
