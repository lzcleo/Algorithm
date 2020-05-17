package homework.oj.six;

import java.util.Scanner;

/**
 * @author leolu
 * @create 2019-11-05-18:16
 *
 * 合并排序 每次从中间位置将数组分组再分别排序，实现递归方案
 *
 * 输入
 * 每一行表示一个元素为正整数的数组，所有值用空格分开，第一个为数值长度，其余维数组元素值
 * 13 24 3 56 34 3 78 12 29 49 84 51 9 100
 *
 * 输出
 * 3 3 9 12 24 29 34 49 51 56 78 84 100
 *
 **/
public class Solution8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] is = new int[n];
            for (int j = 0; j < n; j++) {
                is[j] = scanner.nextInt();
            }
            QuickSort(is, 0, is.length - 1);
            print(is);

        }
    }

    private static int PartSort(int[] arr, int low, int high) {
        int data = arr[low];
        /*
         * 一次遍历的方法：插空法 定义一个data将arr[low]存起来，并把这个位置挖空
         */
        while (low < high) {
            while (low < high && arr[high] >= data) {
                high--;
            }
            arr[low] = arr[high];
            /**
             * 从high，也就是后面往前遍历 找到比键值小的数据 插入到前面留下的空中 high位再次留下空来
             */

            while (low < high && arr[low] <= data) {
                low++;
            }
            arr[high] = arr[low];

        }
        arr[low] = data;
        /*
         * 循环退出后 low和high重合 将将键值赋给第low，并将low返回
         */
        return low;
    }

    private static void QuickSort(int[] arr, int low, int high) {
        if(low<high) {
            //防止发生栈溢出异常
            int index = PartSort(arr, low, high);
            QuickSort(arr, low, index - 1);
            QuickSort(arr, index + 1, high);
        }

    }
    private static void print(int[] os) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : os) {
            stringBuilder.append(i).append(' ');

        }
        System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
    }
}
