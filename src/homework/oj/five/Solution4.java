package homework.oj.five;

import java.util.*;

import java.util.HashMap;

/**
 * @author leolu
 * @create 2019-10-31-21:24
 **/


public class Solution4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        getPairs(sc);
    }

    public static void getPairs(Scanner sc) {
        int testNum = sc.nextInt();
        while (testNum-- > 0) {
            int pairSize = sc.nextInt();
            long[][] pairs = new long[pairSize][2];
            long xMin = 1000000000, xMax = 0, yMin = 1000000000, yMax = 0;
            HashMap<Long, Integer> mapX = new HashMap<>();
            HashMap<Long, Integer> mapY = new HashMap<>();
            HashMap<Long, Long> pair = new HashMap<>();

            for (int i = 0; i < pairSize; ++i) {
                pairs[i][0] = sc.nextLong();
                pairs[i][1] = sc.nextLong();
                if (!(pair.containsKey(pairs[i][0]) && pair.get(pairs[i][0]) == pairs[i][1])) {
                    pair.put(pairs[i][0], pairs[i][1]);
                    if (!mapX.containsKey(pairs[i][0])) {
                        mapX.put(pairs[i][0], 1);
                    } else {
                        int value = mapX.get(pairs[i][0]);
                        mapX.put(pairs[i][0], ++value);
                    }

                    if (!mapY.containsKey(pairs[i][1])) {
                        mapY.put(pairs[i][1], 1);
                    } else {
                        int value = mapY.get(pairs[i][1]);
                        mapY.put(pairs[i][1], ++value);
                    }
                }
            }

            int res = 0;
            for (Long key : mapX.keySet()) {
                res += mapX.get(key) * (mapX.get(key) - 1) / 2;
            }

            for (Long key : mapY.keySet()) {
                res += mapY.get(key) * (mapY.get(key) - 1) / 2;
            }

            System.out.println(res);
        }
    }
}


