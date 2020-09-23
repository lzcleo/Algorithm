package leetcode.Other;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class x的平方根 {
    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        System.out.println(decimalFormat.format(sqrtByNewton(5, 0.000000000001)));
        System.out.println(decimalFormat.format(sqrt(5,0.000000000001)));
    }

    public static double sqrtByNewton(int n, double precision) {
        double last = n; //保存上一个计算的值
        double val = (n + 1) >> 1;
        while (Math.abs(val - last) > precision) {
            last = val;
            val = (val + n / val) / 2;
        }
        return val;
    }

    public static double sqrt(int n, double precision){
        double start = 0;
        double end = (double) n;
        double last = end;
        double mid = (start + end) / 2.0;
        while (Math.abs(last - mid) > precision){
            if (mid * mid > n){
                end = mid;
            }else {
                start = mid;
            }
            last = mid;
            mid = (start + end) / 2.0;
        }
        return mid;
    }
}
