package leetcode.string;

/**
 * @author leolu
 * @create 2020-04-15-17:
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式
 **/
public class 字符串相乘 {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        int m = num1.length();
        int n = num2.length();
        //数字num1和num2相乘，位数不会超过m+n，这里忽略最高位为两位数的情况，所以只申请了m+n-1个位置
        int[] intRes = new int[m + n - 1];
        //例如 25*25 ，折申请了一个长度为3的int数组
        //i为0 ，j为0时，2*2为4，加到i+j位置上， 4 0 0
        //i为0 ，j为1时，2*5为10，加到i+j位置上， 4 10 0
        //i为1 ，j为0时，5*2为10，加到i+j位置上， 4 20 0
        //i为1 ，j为1时，5*5为25，加到i+j位置上， 4 20 25

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                intRes[i + j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        //再分别把每个位上超过10的进位
        for (int i = intRes.length - 1; i > 0; i--) {
            if (intRes[i] >= 10) {
                intRes[i - 1] += intRes[i] / 10;
                intRes[i] %= 10;
            }
        }

        String res = "";
        for (int i = 0; i < intRes.length; i++) {
            res += String.valueOf(intRes[i]);
        }
        return res;
    }
}
