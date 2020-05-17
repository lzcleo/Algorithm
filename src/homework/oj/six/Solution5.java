package homework.oj.six;

import java.util.Scanner;

/**
 * @author leolu
 * @create 2019-11-05-18:16
 *
 * 实现冒泡排序
 *
 * 输入
 * 每一行表示一个元素为正整数的数组，所有值用空格隔开，第一个值为数组长度，其余的为数组元素值
 *  13 24 3 56 34 3 78 12 29 49 84 51 9 100
 *
 *  输出
 *  每一行为排序结果，用空格隔开，末尾不要空格
 *  3 3 9 12 24 29 34 49 51 56 78 84 100
 *
 **/
public class Solution5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] is = new int[n];
            for (int j = 0; j < n; j++) {
                is[j] = scanner.nextInt();
            }
            bubbleSort(is);
            print(is);

        }
    }

    private static void print(int[] os) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : os) {
            stringBuilder.append(i).append(' ');

        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
    }

    private static void bubbleSort(int[] is) {
        int temp;
        int i;
        // 外层循环：n个元素排序，则至多需要n-1趟循环
        for (i = 0; i < is.length - 1; i++) {
            // 定义一个布尔类型的变量，标记数组是否已达到有序状态
            boolean flag = true;
            /*内层循环：每一趟循环都从数列的前两个元素开始进行比较，比较到无序数组的最后*/
            for (int j = 0; j < is.length - 1 - i; j++) {
                // 如果前一个元素大于后一个元素，则交换两元素的值；
                if (is[j] > is[j + 1]) {
                    temp = is[j];
                    is[j] = is[j + 1];
                    is[j + 1] = temp;
                    //本趟发生了交换，表明该数组在本趟处于无序状态，需要继续比较；
                    flag = false;
                }
            }
            //根据标记量的值判断数组是否有序，如果有序，则退出；无序，则继续循环。
            if (flag) {
                break;
            }
        }
    }
}
