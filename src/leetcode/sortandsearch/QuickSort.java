package leetcode.sortandsearch;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author leolu
 * @create 2020-01-16-21:56
 **/
public class QuickSort {
    private static int count;
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        int[] num = {25, 84, 21, 47, 15, 27, 68 ,35 ,20};
        System.out.println(arrayToString(num,"未排序"));
        quickSort(num,0,num.length-1);
        System.out.println(arrayToString(num,"排序"));
        System.out.println("数组个数："+num.length);
//        System.out.println("循环次数："+count);


    }

    /**
     * 将一个int类型数组转化为字符串
     * @param arr
     * @param flag
     * @return
     */
    private static String arrayToString(int[] arr,String flag) {
        String str = "数组为("+flag+")：";
        for(int a : arr) {
            str += a + "\t";
        }
        return str;
    }



    private static int partition(int[] nums, int l, int r) {
        int i = l, j = r + 1;
        while (true) {
            while (nums[++i] < nums[l] && i < r);
            while (nums[--j] > nums[l] && l < j);
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    public static void swap(int nums[], int a, int b)// 数组中两个元素互换
    {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right)
            return;
        int partition = partition(nums, left, right);

        quickSort(nums, left, partition - 1);

        quickSort(nums, partition + 1, right);


    }



}
