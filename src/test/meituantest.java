package test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author leolu
 * @create 2020-06-02-23:09
 **/
public class meituantest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.MAX_VALUE);
        list.add(Integer.MIN_VALUE);
        Collections.sort(list);
        System.out.println(list);
    }
}
