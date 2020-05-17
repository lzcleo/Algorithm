package homework.oj.second;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author shkstart
 * @create 2019-10-17-15:53
 **/
public class Solution6 {
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
            int sum = scanner.nextInt();

            Map<Integer, Integer> map = new HashMap<>();
            int count = 0;
            for (int k = 0; k < is.length; k++) {
                if (map.get(sum - is[k]) != null) {
                    count++;
                }
                map.put(is[k], k);
            }
            System.out.println(count);
//
//            Arrays.sort(is);
//            System.out.println(countSum(is, 0, is.length - 1, sum));

        }
    }

    private static int countSum(int[] is, int l, int r, int sum) {
        if (l >= r) {
            return 0;
        }
        int s = is[l] + is[r];
        if (s == sum) {
            return 1 + countSum(is, l + 1, r - 1, sum);
        } else {
            return Math.max(countSum(is, l, r - 1, sum), countSum(is, l + 1, r, sum));
        }
    }
}
