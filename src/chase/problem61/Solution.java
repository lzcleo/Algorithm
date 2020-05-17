package chase.problem61;

import java.util.Arrays;

/**
 * @author leolu
 * @create 2020-02-10-13:30
 * 扑克牌中的顺子
 **/
public class Solution {
    public static boolean isContinuous(int[] numbers) {
        if (numbers.length <= 1)
            return true;
        Arrays.sort(numbers);
        int numbersOfZero = 0;
        int numbersOfGap = 0;
        for (int i = 0;i < numbers.length && numbers[i] == 0;i ++){
            numbersOfZero ++;
        }
        int small = numbersOfZero;
        int big = numbersOfZero + 1;
        while (big < numbers.length){
            if(numbers[small] == numbers[big])
                return false;

            numbersOfGap += numbers[big] - numbers[small] - 1;
            small = big;
            big ++;
        }
        return (numbersOfGap > numbersOfZero) ? false : true;
    }

    public static void main(String[] args) {
        int[] numbers = {1,3,2,5,4};
        System.out.println(isContinuous(numbers));
    }
}
