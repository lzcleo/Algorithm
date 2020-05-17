package homework.oj.third;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author shkstart
 * @create 2019-10-17-19:39
 **/
public class Solution3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < times; i++) {
            int num = sc.nextInt();
            int[] nums = new int[num];
            for (int k = 0; k < num; k++) {
                nums[k] = sc.nextInt();
            }

            List<Double> result = new ArrayList<>();
            for (int j = 0; j < num - 1; j++) {
                double num1 = nums[j];
                double num2 = nums[j];
                double num3 = nums[j + 1];
                double t = 0;
                double left, right;
                while (true) {

                    left = 0;
                    right = 0;
                    for (int index = 0; index <= j; index++) {
                        left += 1.0 / (num1 - nums[index]);
                    }

                    for (int index = j + 1; index < num; index++) {
                        right += 1.0 / (nums[index] - num1);
                    }
                    if (left - right > 0.00000001) {
                        t = num1;
                        num1 = (num1 + num3) / 2.0;
                        num2 = t;
                    } else if (right - left > 0.00000001) {
                        t = num1;
                        num1 = (num2 + num1) / 2.0;
                        num3 = t;
                    } else {
                        break;

                    }
                }
                result.add(num1);
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (Double d : result) {
                stringBuilder.append(df.format(d)).append(" ");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            System.out.println(stringBuilder.toString());
        }
    }

}



