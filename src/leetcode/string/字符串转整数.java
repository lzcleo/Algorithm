package leetcode.string;

import org.junit.Test;

/**
 * @author leolu
 * @create 2020-03-21-22:14
 * 字节面试可千万别碰到这题
 **/
public class 字符串转整数 {
    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        int n = str.length();
        int i = 0;
        int res = 0;
        boolean is_negative = false;
        //第一步，跳过前面若干个空格
        while (i < n && str.charAt(i) == ' ') {
            ++i;
        }
        //如果字符串全是空格直接返回
        if (i == n) {
            return 0;
        }
        //第二步，判断正负号
        if (str.charAt(i) == '-') {
            is_negative = true;
        }
        //如果是正负号，还需要将指针i，跳过一位
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            ++i;
        }
        //第三步，循环判断字符是否在 0~9之间
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            //'0'的ASCII码是48，'1'的是49，这么一减就从就可以得到真正的整数值
            int tmp = str.charAt(i) - 48;
            //判断是否大于 最大32位整数
            if (!is_negative && (res > 214748364 || (res == 214748364 && tmp >= 7))) {
                return 2147483647;
            }
            //判断是否小于 最小32位整数
            if (is_negative && (-res < -214748364 || (-res == -214748364 && tmp >= 8))) {
                return -2147483648;
            }
            res = res * 10 + tmp;
            ++i;
        }
        //如果有负号标记则返回负数
        if (is_negative) {
            return -res;
        }
        return res;
    }

    //牛客
    public int StrToInt(String s) {
        String trim = s.trim();
        if (trim == null || trim.length() == 0)
            return 0;
        boolean isNegative = trim.charAt(0) == '-';
        int ret = 0;
        for (int i = 0; i < trim.length(); i++) {
            char c = trim.charAt(i);
            if (i == 0 && (c == '+' || c == '-'))  /* 符号判定 */
                continue;
            if (c < '0' || c > '9')                /* 非法输入 */
                break;
            ret = ret * 10 + (c - '0');
        }
        return isNegative ? -ret : ret;
    }

    @Test
    public void test() {
        System.out.println(StrToInt("          -123445   word         "));

    }

}
