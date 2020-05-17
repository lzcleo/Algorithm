package homework.oj.seven;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author leolu
 * @create 2019-11-21-12:01
 *
 * 最大和子数组
 *
 * 输入
 * 1
 * 5
 * 1 2 3 -4 5
 *
 * 输出
 * 11
 **/
public class Solution1 {
}

class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int numOfTestCases = scanner.nextInt();
        while (numOfTestCases-- > 0)
        {
            int lengthOfArray = scanner.nextInt();
            int[] array = new int[lengthOfArray];
            for (int i = 0; i < lengthOfArray; i++)
            {
                array[i] = scanner.nextInt();
            }
            System.out.println(calMaxSubSumByRemoveOneNum(array, lengthOfArray));
        }
    }

    private static int calMaxSubSumByRemoveOneNum(int[] array, int lengthOfArray)
    {
        int forward[] = new int[lengthOfArray];
        int backward[] = new int[lengthOfArray];

        int curMax = array[0];
        int maxSoFar = array[0];
        forward[0] = array[0];
        for (int i = 1; i < lengthOfArray; i++)
        {
            curMax = Math.max(array[i], curMax + array[i]);
            maxSoFar = Math.max(maxSoFar, curMax);

            forward[i] = curMax;
        }

        curMax = maxSoFar = backward[lengthOfArray - 1] = array[lengthOfArray - 1];
        for (int i = lengthOfArray - 2; i >= 0; i--)
        {
            curMax = Math.max(array[i], curMax + array[i]);
            maxSoFar = Math.max(maxSoFar, curMax);
            backward[i] = curMax;
        }

        int fans = maxSoFar;
        for (int i = 1; i < lengthOfArray - 1; i++)
        {
            fans = Math.max(fans, forward[i - 1] + backward[i + 1]);
        }
        return fans;
    }
}


