package leetcode.string;

import org.junit.Test;

/**
 * @author leolu
 * @create 2020-03-20-17:16
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000
 * 这道题大体上有三种思路
 * 1 暴力法-时间复杂度为O（n^3），空间复杂度O（1）
 * 2 动归-时间复杂度为O(n^2)，空间复杂度O(n^2)
 * 3 扩展中心-时间复杂度为O(n^2)，空间复杂度O(1)
 **/
public class 最长回文子串 {
    private boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // 暴力解法
    public String longestPalindromeSolutionFirst(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && test.length() > max) {
                    ans = s.substring(i, j);
                    max = Math.max(max, ans.length());
                }
            }
        return ans;
    }

    public String longestPalindromeSolutionSecond(String s) {
        if (s.equals(""))
            return "";
        String origin = s;
        String reverse = new StringBuffer(s).reverse().toString();
        int length = s.length();
        int[][] arr = new int[length][length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++) {
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                }
                /**********修改的地方*******************/
                if (arr[i][j] > maxLen) {
                    int beforeRev = length - 1 - j;
                    if (beforeRev + arr[i][j] - 1 == i) { //判断下标是否对应
                        maxLen = arr[i][j];
                        maxEnd = i;
                    }
                    /*************************************/
                    /**
                     * i，j 始终指向子串的末尾字符。所以 j 指向的红色的 a 倒置前的下标是 beforeRev = length-1-j=4-1-2=1，
                     * 对应的是字符串首位的下标，我们还需要加上字符串的长度才是末尾字符的下标，也就是 beforeRev+arr\[i][j]-1=1+3-1=3，
                     * 因为 arr\[i][j] 保存的就是当前子串的长度，也就是图中的数字 3。此时再和它与 i 比较，如果相等，
                     * 则说明它是我们要找的回文串。
                     */
                }
            }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    public String longestPalindromeSolutionThree(String s) {
        if (s == null && s.length() == 0)
            return null;
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int expandFirst = expand(s, i, i);
            int expandSecond = expand(s, i, i + 1);
            int max = Math.max(expandFirst, expandSecond);
            if (max > end - start + 1) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
            //此处逻辑一开始没有写对
        }
        return s.substring(start, end + 1);
    }

    private int expand(String s, int left, int right) {
        int l = left;
        int r = right;
        while (l >= 0 && r <= s.length() - 1 && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
//        return r - l ;
        return r - l - 1;
    }

    @Test
    public void test() {
        System.out.println(longestPalindromeSolutionThree("abababacd"));
        System.out.println(longestPalindromeSolutionFirst("abababacd"));
        System.out.println(longestPalindromeSolutionSecond("abababacd"));
    }
}
