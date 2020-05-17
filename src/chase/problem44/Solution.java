package chase.problem44;

import static java.lang.Math.pow;

/**
 * @author leolu
 * @create 2020-02-03-16:07
 * 数字序列中某一位的数字
 * 数字以01234567891011121314的格式序列化排位
 * 求第N位对应的数字
 *
 **/
public class Solution {
    public static void main(String[] args) {
        System.out.println(digitAtIndex(1002));
    }

    static int countOfIntegers(int digits){
        if (digits == 1){
            return 10;
        }
        int count = (int) pow(10,digits - 1);
        return digits * count;
    }

    static int beginNumber(int digits){
        if (digits == 1){
            return 0;
        }
        return (int) pow(10,digits - 1);
    }

    static int digitAtIndex(int index,int digits){
        int number = beginNumber(digits)+index/digits;
        int indexFromRight = digits - index % digits;
        for (int i = 0; i < indexFromRight; i++) {
            number /= 10;
        }
        return number % 10;
    }

    static int digitAtIndex(int index){
        if (index < 0)
            return -1;
        int digits = 1;
        while (true){
            int numbers = countOfIntegers(digits);
            if (index < numbers * digits){
                return digitAtIndex(index,digits);
            }
            index -= digits * numbers;
            digits ++;

        }
    }



}
