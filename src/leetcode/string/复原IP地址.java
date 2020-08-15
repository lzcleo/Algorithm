package leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author leolu
 * @create 2020-04-11-18:00
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 **/
public class 复原IP地址 {
    //    int n;
//    String s;
//    LinkedList<String> segments = new LinkedList<String>();
//    ArrayList<String> output = new ArrayList<String>();
//
//    public boolean valid(String segment) {
//    /*
//    Check if the current segment is valid :
//    1. less or equal to 255
//    2. the first character could be '0'
//    only if the segment is equal to '0'
//    */
//        int m = segment.length();
//        if (m > 3)
//            return false;
//        return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
//    }
//
//    public void update_output(int curr_pos) {
//    /*
//    Append the current list of segments
//    to the list of solutions
//    */
//        String segment = s.substring(curr_pos + 1, n);
//        if (valid(segment)) {
//            segments.add(segment);
//            output.add(String.join(".", segments));
//            segments.removeLast();
//        }
//    }
//
//    public void backtrack(int prev_pos, int dots) {
//    /*
//    prev_pos : the position of the previously placed dot
//    dots : number of dots to place
//    */
//        // The current dot curr_pos could be placed
//        // in a range from prev_pos + 1 to prev_pos + 4.
//        // The dot couldn't be placed
//        // after the last character in the string.
//        int max_pos = Math.min(n - 1, prev_pos + 4);
//        for (int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
//            String segment = s.substring(prev_pos + 1, curr_pos + 1);
//            if (valid(segment)) {
//                segments.add(segment);  // place dot
//                if (dots - 1 == 0)      // if all 3 dots are placed
//                    update_output(curr_pos);  // add the solution to output
//                else
//                    backtrack(curr_pos, dots - 1);  // continue to place dots
//                segments.removeLast();  // remove the last placed dot
//            }
//        }
//    }
//
//    public List<String> restoreIpAddresses(String s) {
//        n = s.length();
//        this.s = s;
//        backtrack(-1, 3);
//        return output;
//    }
//
    private List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() == 0 || s == null) return res;
        int n = s.length();
        generate(s, 0, n, 4, new LinkedList<>());
        return res;
    }

    private void generate(String s, int start, int len, int leftPart, LinkedList<String> path) {
        if (start == len) {
            if (leftPart == 0) {
                res.add(String.join(".", path));
            }
        }
        for (int i = start; i < start + 3; i++) {
            if (i > len) break;
            if (((len - i) / leftPart) > 3) {
                continue;
            }

            if (check(s, start, i)) {
                String cur = s.substring(start, i + 1);
                path.offerLast(cur);
                generate(s, start + 1, len, leftPart - 1, path);
                path.removeLast();
            }
        }
    }

    private boolean check(String s, int left, int right) {
        if (s.length() > 1 && s.charAt(left) == '0') {
            return false;
        }
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum *= 10;
            sum += (s.charAt(left) - '0');
        }
        return (sum > 0 && sum <= 255);
    }

    @Test
    public void test(){
        List<String> strings = restoreIpAddresses("25525511135");
        System.out.println(strings);
    }
}
