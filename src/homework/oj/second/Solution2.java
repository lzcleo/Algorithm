package homework.oj.second;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author shkstart
 * @create 2019-10-14-16:08
 * Description
 * 给定一个矩形区域，每一个位置上都是1或0，求该矩阵中每一个位置上都是1的最大子矩形区域中的1的个数。
 * Input
 * 输入第一行为测试用例个数。每一个用例有若干行，第一行为矩阵行数n和列数m，下面的n行每一行是用空格隔开的0或1。
 * Output
 * 输出一个数值。
 * Sample Input 1
 * 1
 * 3 4
 * 1 0 1 1
 * 1 1 1 1
 * 1 1 1 0
 * Sample Output 1
 * 6
 **/
public class Solution2 {

}

class Solution {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int testNum = input.nextInt();
        for (int i = 0; i < testNum; i++) {
            input.nextLine();
            int m = input.nextInt();
            int n = input.nextInt();
            input.nextLine();
            int[][] matrix = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    matrix[j][k] = input.nextInt();
                }
            }


            System.out.println(maximalRectangle(matrix));
        }

    }

    private static int maximalRectangle(int[][] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = 0;


        //只含0，1的数组转换成不只是包含0，1的统计形式
        for (int i = 0; i < nums[0].length; i++) {
            int sum1 = 1;
            for (int j = nums.length - 1; j >= 0; j--) {
                if (nums[j][i] == 1) {
                    nums[j][i] = sum1;
                    sum1++;
                } else {
                    sum1 = 1;
                }
            }
        }


        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                if (nums[i][j] != 0) {
                    //连续非0数的个数
                    int sum2 = 1;
                    //连续非0数中最小值
                    int min = nums[i][j];

                    if (nums[i][j] > res) {
                        res = nums[i][j];
                    }
                    for (int k = j + 1; k < nums[0].length; k++) {
                        if (nums[i][k] != 0) {
                            sum2++;
                            if (nums[i][k] < min) {
                                min = nums[i][k];
                            }
                            int tem_res = sum2 * min;
                            if (tem_res > res) {
                                res = tem_res;
                            }
                        } else {
                            break;
                        }
                    }

                }
            }
        }

        return res;
    }


}