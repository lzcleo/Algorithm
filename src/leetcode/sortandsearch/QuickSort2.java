package leetcode.sortandsearch;

/**
 * @author leolu
 * @create 2020-02-01-21:12
 **/
public class QuickSort2 {
    public static void main(String[] args) {
        int []array = {21, 34, 74, 3, 20, 2, 56, 46, 6};
        quickSort2(array, 0, array.length - 1);
        System.out.println(arrayToString(array,""));
    }


    static void quickSort2(int[] arr, int begin, int end) {
        if (begin < end){
            int p = partition2(arr, begin, end);
            // int p = partition2(arr, begin, end);
            quickSort2(arr, begin, p - 1);
            quickSort2(arr, p + 1, end);
        }
    }

    static int partition2(int arr[], int begin, int end){
        int pivotIndex = begin;
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, end);

        int big = begin - 1; // index of smaller element
        for (int small = begin; small <= end - 1; small++){
            // 遇到一个元素小于pivot
            if (arr[small] <= pivot){
                big++;
                swap(arr, big, small);
            }
        }
        swap(arr, big + 1, end);
        return big + 1;
    }

    public static void swap(int arr[], int a, int b)// 数组中两个元素互换
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static String arrayToString(int[] arr,String flag) {
        String str = "数组为("+flag+")：";
        for(int a : arr) {
            str += a + "\t";
        }
        return str;
    }

}
