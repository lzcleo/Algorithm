package homework.oj.second;

import java.util.Arrays;

import java.util.Scanner;

/**
 * @author shkstart
 * @create 2019-10-13-16:21
 **/
public class Solution3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {
            scanner.nextLine();
            String s = scanner.nextLine();
            String[] ss = s.split(" ");
            int n = scanner.nextInt();
            int[] is = new int[ss.length];
            for (int j = 0; j < ss.length; j++) {
                is[j] = Integer.parseInt(ss[j]);
            }

            int sum = 0;
            int max = 0;
            int maxIndex = 0;
            for (int index = 0; index < n; index++) {
                if (is[index] > max) {
                    max = is[index];
                    maxIndex = index;
                }
            }
            sum += max;
            for (int index = 1; index < is.length - n + 1; index++) {
                if (maxIndex >= index && is[index + n - 1] > max) {
                    max = is[index + n - 1];
                    maxIndex = index + n - 1;
                } else {
                    max = max(is, index, index + n);
                    maxIndex = index(is, max, index, index + n);
                }
                sum += max;

            }
            System.out.println(sum);
        }
    }

    private static int max(int[] a) {
        int max = 0;
        for (int i : a) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    private static int max(int[] a, int begin, int end) {
        return max(Arrays.copyOfRange(a, begin, end));
    }

    private static int index(int[] a, int i) {
        for (int index = 0; index < a.length; index++) {
            if (a[index] == i) {
                return index;
            }
        }
        return -1;
    }

    private static int index(int[] a, int i, int begin, int end) {
        return index(Arrays.copyOfRange(a, begin, end), i) + begin;

    }

}
