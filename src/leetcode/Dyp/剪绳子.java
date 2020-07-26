package leetcode.Dyp;

import java.util.PriorityQueue;

public class 剪绳子 {

    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 1; i <= n; i ++) {
            for (int j = 1; j < i; j ++) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, (i - j) * dp[j]));
            }
        }
        return dp[n];
    }

    public int cuttingRopeSecond(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        if (n == 3) return 2;
        int threeSize = n / 3;
        if (n % 3 == 1) threeSize --;
        int twoSize = (n - (threeSize * 3)) / 2;
        //注意转成int型否则编译报错
        return (int)(Math.pow(3, threeSize)) * (int)(Math.pow(2, twoSize));
    }

    private PriorityQueue<Integer> min = new PriorityQueue<>();
    private PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b - a);
    private int len;

    public void Insert(Integer num) {
        if (len % 2 == 0) {
            max.add(num);
            min.add(max.poll());
        } else {
            min.add(num);
            max.add(min.poll());
        }
        len ++;
    }

    public static int majorityElement(int[] nums) {
        int maj = 0, cnt = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (cnt == 0) {
                maj = nums[i];
                cnt = 1;
            } else {
                if (nums[i] == maj) {
                    cnt ++;
                } else {
                    cnt --;
                }
            }
        }
        cnt = 0;
        for (int num : nums) {
            if (num == maj) cnt ++;
        }
        return cnt >= nums.length ? maj : -1;
    }

    public static void main(String[] args) {
        int[] ints = {1,2,3,2,2,2,5,4,2};
        int i = majorityElement(ints);
    }

}
