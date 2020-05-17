package homework.oj.third;

import java.util.Scanner;

/**
 * @author shkstart
 * @create 2019-10-17-21:11
 **/
public class Solution4 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int t = 0; t < n; ++t)
        {
            int size = sc.nextInt();
            int[] boards = new int[size];
            for (int i = 0; i < size; i++)
            {
                boards[i] = sc.nextInt();
            }
            int people = sc.nextInt();
            System.out.println(select(boards, people));
        }
    }

    public static int select(int[] arr, int num)
    {
        if (arr == null || arr.length == 0 || num < 1)
        {
            throw new RuntimeException("err");
        }
        if (arr.length < num) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i != arr.length; i++)
            {
                max = Math.max(max, arr[i]);
            }
            return max;
        }
        else
        {
            int min_sum = 0;
            int max_sum = 0;
            for (int n : arr)
            {
                max_sum += n;
            }
            while (min_sum != max_sum - 1)
            {
                int mid = (min_sum + max_sum) / 2;
                if (getNeedNum(arr, mid) > num)
                {
                    min_sum = mid;
                }
                else
                {
                    max_sum = mid;
                }
            }
            return max_sum;
        }
    }

    public static int getNeedNum(int[] arr, int lim)
    {
        int res = 1;
        int step_sum = 0;
        for (int i = 0; i != arr.length; i++)
        {
            if (arr[i] > lim)
            {
                return Integer.MAX_VALUE;
            }
            step_sum += arr[i];
            if (step_sum > lim)
            {
                res++;
                step_sum = arr[i];
            }
        }
        return res;
    }
}

