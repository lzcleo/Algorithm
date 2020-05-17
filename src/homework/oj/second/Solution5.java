package homework.oj.second;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author shkstart
 * @create 2019-10-17-15:55
 * 找到给定数组的给定区间里的第k小的值
 *
 * 输入
 * 1
 * 1
 **/
public class Solution5 {
}


class Main{

    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);

        int times = scanner.nextInt();



        for (int i = 0; i < times; i++) {

            scanner.nextLine();

            String s = scanner.nextLine();

            String[] ss = s.split(" ");

            int[] is = new int[ss.length];

            for (int j = 0; j < ss.length; j++) {

                is[j] = Integer.parseInt(ss[j]);

            }

            int start = scanner.nextInt();

            int end = scanner.nextInt();

            int K = scanner.nextInt();

            is = Arrays.copyOfRange(is, start - 1, end);

            noK(is, 0, is.length - 1, K);

            System.out.println(is[K - 1]);

        }

    }







    private static void noK(int[] a, int start, int end, int K) {

        if (start != end) {

            int num = 0;

            if (start < end)

                num = checkKthNumber(a, start, end);

            if (num == K-1) return;

            if (num > K-1) {

                noK(a, start, num - 1, K);

            } else {

                noK(a, num + 1, end, K);

            }

        }

    }



    private static int checkKthNumber(int[] a, int start, int end) {

        int l = start;

        int r = end;

        int key = a[start];

        while (l < r) {

            while (l < r && a[r] >= key) r--;

            a[l] = a[r];

            while (l < r && a[l] < key) l++;

            a[r] = a[l];

        }

        a[l] = key;

        return l;

    }
}
