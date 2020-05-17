package homework.oj.first;

import java.util.*;

/**
 * @author shkstart
 * @create 2019-09-26-20:24
 **/
public class Solu1 {


    public static void sortByArray(int[] array1,int[] array2){
        Map<Integer,Integer> countMap=new TreeMap<>();
        for (int value : array1) {
            countMap.merge(value, 1, Integer::sum);
        }
        int j=0;
        for (int value : array2) {
            Integer count = countMap.getOrDefault(value, 0);
            while (count > 0) {
                array1[j] = value;
                count--;
                j++;
            }
            countMap.remove(value);
        }
        for(Map.Entry<Integer,Integer> entry:countMap.entrySet()){
            Integer count=entry.getValue();
            while (count>0){
                array1[j]=entry.getKey();
                count--;
                j++;
            }
        }
    }
}
