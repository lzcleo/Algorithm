package leetcode.Other;

/**
 * @author leolu
 * @create 2020-04-12-17:29
 **/
public class 到n整数中1出现的次数 {
    /**
     * 思路是分别计算个位、十位、百位........上出现 1 的个数。
     * 以  n =216为例：
     * 个位上： 1 ，11，21，31，.....211。个位上共出现（216/10）+ 1个 1 。因为除法取整，
     * 210~216间个位上的1取不到，所以我们加8进位。你可能说为什么不加9，n=211怎么办，这里把最后取到的个位数为1的单独考虑，先往下看。
     * 十位上：10~19，110~119，210~216.   十位上可看成 求（216/10）=21 个位上的1的个数然后乘10。
     * 这里再次把最后取到的十位数为1的单独拿出来，即210~216要单独考虑 ，个数为（216%10）+1 .这里加8就避免了判断的过程。
     * 后面以此类推。
     * 时间复杂度 O(logN)
     * <p>
     * 改日再看 属实没看懂
     *
     * @param n
     * @return
     */
    public int countDigitOneFirst(int n) {
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }


    //https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/javadi-gui-by-xujunyi/
    public int countDigitOne(int n) {
        return count(n);
    }

    private int count(int n) {
        if (n <= 0)
            return 0;
        String s = String.valueOf(n);
        int high = s.charAt(0) - '0';
        int pow = (int) Math.pow(10, s.length() - 1);
        int last = n - high * pow;
        if (high == 1) {
            return count(pow - 1) + last + 1 + count(last);
        } else {
            return pow + high * count(pow - 1) + count(last);
        }
    }

    //https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/
    //叹服
    public int countDigitOneThird(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) res += high * digit;
            else if(cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }


    /**
     * 二进制中1出现的次数
     * @param n
     * @return
     */
    public int numberOf1(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n &= (n - 1);
        }
        return cnt;
    }

}
