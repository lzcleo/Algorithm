package test;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test {
    private List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if (s.length() == 0 || s == null) return res;
        int n = s.length();
        generate(s, 0, n, 4, new LinkedList<String>());
        return res;
    }

    private void generate(String s, int start, int len, int leftPart, LinkedList<String> path) {
        if (start == len) {
            if (leftPart == 0) {
                res.add(String.join(".", path));
            }
        }
        for (int i = start; i < start + 3; i ++) {
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

    private boolean check (String s, int left, int right) {
        if (s.length() > 1 && s.charAt(left) == '0') {
            return false;
        }
        int sum = 0;
        for (int i = left; i <= right; i ++) {
            sum *= 10;
            sum += (s.charAt(left) - '0');
        }
        return (sum > 0 && sum <= 255);
    }


}

