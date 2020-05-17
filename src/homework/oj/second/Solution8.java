package homework.oj.second;

import java.util.Scanner;

/**
 * @author shkstart
 * @create 2019-10-17-10:06
 *
 * 调整数组使差最小
 * 有两个序列a b，大小都为n，序列元素的值任意整数，无序，要求通过交换a ，b中的元素，使序列a元素的和与序列b元素的和之间的差最小
 *
 * 输入
 * 1
 * 100 99 98 1 2 3
 * 1 2 3 4 5 40
 *
 * 输出
 * 48
 *
 **/
public class Solution8 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int times = scanner.nextInt();

        scanner.nextLine();

        for (int i = 0; i < times; i++) {


            String s = scanner.nextLine();

            String[] ss = s.split(" ");

            int[] is = new int[ss.length * 2];

            for (int j = 0; j < ss.length; j++) {

                is[j] = Integer.parseInt(ss[j]);

            }

            s = scanner.nextLine();

            ss = s.split(" ");

            for (int j = ss.length; j < ss.length * 2; j++) {

                is[j] = Integer.parseInt(ss[j - ss.length]);

            }

            int sum = 0;

            for (int j : is) {

                sum += j;

            }

            double half = sum / 2.0;
            System.out.println(new Double(cap(half, is, 0, is.length / 2) * 2).intValue());
        }
    }



    private static Double cap(double sum, int[] is, int index, int count) {

        if (count == 0) {

            return sum;

        }

        if (index == is.length) {

            return Double.MAX_VALUE;

        }

        if (is[index] > sum) {

            return cap(sum, is, index + 1, count);

        } else {

            return Math.min(cap(sum - is[index], is, index + 1, count - 1), cap(sum, is, index + 1, count));

        }

    }
}