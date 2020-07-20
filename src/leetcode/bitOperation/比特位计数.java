package leetcode.bitOperation;

import org.junit.Test;

public class 比特位计数 {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; ++i)
            ans[i] = popcount(i);
        return ans;
    }

    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; ++count)
            x &= x - 1; //zeroing out the least significant nonzero bit
        return count;
    }


    //我们对0-7的二进制进行比较后发现，2在0的基础上加了一个1，6在2的基础上加了一个1
    //故状态转移方程为f(x+b) = f(x) + 1,x < b, 其中b为2的倍数
    public int[] countBitsSecond(int num) {
        int[] ans = new int[num + 1];
        int i = 0, b = 1;
        // [0, b) is calculated
        while (b <= num) {
            // generate [b, 2b) or [b, num) from [0, b)
            while (i < b && i + b <= num) {
                ans[i + b] = ans[i] + 1;
                ++i;
            }
            i = 0;   // reset i
            b <<= 1; // b = 2b
        }
        return ans;
    }

}
