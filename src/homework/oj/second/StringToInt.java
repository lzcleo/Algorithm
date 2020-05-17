package homework.oj.second;

import java.util.Arrays;

/**
 * @author shkstart
 * @create 2019-10-13-18:31
 **/
public class StringToInt {
    public static void main(String[] args) {
        String[] strings = {"1", "2", "3"};

//        int[] array = Arrays.asList(strings).stream().mapToInt(Integer::parseInt).toArray();
        int[] array = Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();

        for (int i:array) {
            System.out.print(i);
        }
    }
}
