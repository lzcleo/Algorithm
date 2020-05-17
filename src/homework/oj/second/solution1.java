package homework.oj.second;

import java.util.Scanner;

/**
 * @author shkstart
 * @create 2019-10-12-10:42
 **/
public class solution1 {
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

            int count = 0;
            for (int index = 0; index < is.length; index++) {
                int min = is[index];
                int max = is[index];
                for (int index2 = index + 1; index2 < is.length; index2++) {
                    if (is[index2] < min) {
                        min = is[index2];
                    } else if (is[index2] > max) {
                        max = is[index2];
                    }
                    if (max - min > n) {
                        count += is.length - index2;
                        break;
                    }
                }
            }
            System.out.println(count);
        }
    }
}
