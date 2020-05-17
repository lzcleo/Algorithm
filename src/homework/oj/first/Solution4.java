package homework.oj.first;

import org.junit.Test;

import java.util.*;

/**
 * @author shkstart
 * @create 2019-09-26-20:06
 **/
public class Solution4 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n1 = scanner.nextInt();
            int n2 = scanner.nextInt();
            int[] a1 = new int[n1];
            int[] a2 = new int[n2];
            for (int j = 0; j < n1; j++) {
                a1[j] = scanner.nextInt();
            }
            for (int j = 0; j < n2; j++) {
                a2[j] = scanner.nextInt();
            }

            HashMap<Integer, Integer> treeMap = new HashMap<>();
            for (int k : a1) {
                treeMap.merge(k, 1, Integer::sum);
            }

            List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(treeMap.entrySet());
            entries.sort((o1, o2) -> {
                int index1 = index(a2, o1.getKey());
                int index2 = index(a2, o2.getKey());
                if (index1 >= 0 && index2 >= 0) {
                    return index1 < index2 ? -1 : 1;
                } else if (index1 < 0 && index2 < 0) {
                    return o1.getKey() < o2.getKey() ? -1 : 1;
                } else {
                    return index1 < 0 ? 1 : -1;
                }
            });
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<Integer, Integer> entry : entries) {
                for (int j = 0; j < entry.getValue(); j++) {
                    stringBuilder.append(entry.getKey()).append(" ");
                }
            }
            System.out.println(stringBuilder.deleteCharAt(stringBuilder.length()-1));

        }
    }
    private static int index(int[] a, int i) {
        for (int index = 0; index < a.length; index++) {
            if (a[index] == i) {
                return index;
            }
        }
        return -1;
    }
}
