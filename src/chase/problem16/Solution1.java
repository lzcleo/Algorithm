package chase.problem16;

/**
 * @author leolu
 * @create 2019-10-22-22:31
 *
 * 数值的整数次方
 **/
public class Solution1 {



    public double powerWithUnsignedExponent(double base,int exponent){

        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }

        double result = powerWithUnsignedExponent(base , exponent>>1);   //右移运算符代替除以2

        result *= result;

        if (exponent % 2 == 1) {
            result *= base;
        }

        return result;
    }
}
