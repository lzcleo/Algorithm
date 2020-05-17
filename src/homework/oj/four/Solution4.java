package homework.oj.four;


import java.util.Scanner;

/**
 * @author leolu
 * @create 2019-10-27-13:45
 * 13 24 3 56 34 3 78 12 29 49 84 51 9 100
 **/
public class Solution4 {
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
            int[] ints = sort(is);
            for (int j = 0; j < ints.length; j++) {
                if (j<ints.length-1){
                    System.out.print(ints[j]+" ");
                }else{
                    System.out.print(ints[j]);
                }
            }


        }
    }


    public static int[] sort(int[] array) {
        //从第二个元素开始遍历即可
        for (int i = 1; i < array.length; i++) {
            //参考值
            int temp = array[i];
            //从参考值前面一个元素开始从后往前查找
            int j = i - 1;
            for (; j >= 0; j--) {
                //假如找到比参考值大，数据往后移
                if (array[j] > temp) {
                    array[j + 1] = array[j];
                } else {
                    // 跳出循环
                    break;
                }
            }
            array[j + 1] = temp;

        }
        return array;
    }


}


