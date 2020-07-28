package leetcode.Other;

import java.util.ArrayList;

public class 圆圈中最后剩下的数字 {
    //    https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/javajie-jue-yue-se-fu-huan-wen-ti-gao-su-ni-wei-sh/
    public int lastRemaining(int n, int m) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }
        return res;
    }

    private static ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    public static ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        int i = 1, j = 1, curSum = 0;
        while (i <= (sum / 2)) {
            if (curSum < sum) {
                curSum += j;
                j ++;
            } else if (curSum > sum) {
                curSum -= i;
                i ++;
            } else {
                ArrayList<Integer> res = new ArrayList<>();
                for (int m = i; m < j; m ++) {
                    res.add(m);
                }
                result.add(res);
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
