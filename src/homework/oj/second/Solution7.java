package homework.oj.second;

import java.util.*;

/**
 * @author shkstart
 * @create 2019-10-17-10:01
 **/
class Soulution7{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int times = scanner.nextInt();

        scanner.nextLine();

        for (int i = 0; i < times; i++) {

            String s = scanner.nextLine();

            String[] ss = s.split(" ");

            int[] is = new int[ss.length];

            for (int j = 0; j < ss.length; j++) {

                is[j] = Integer.parseInt(ss[j]);

            }



            is = duplicateRemoval(is);



            Set<String> result = new HashSet<>();

            int length = 0;

            for (int k = 0; k < is.length; k++) {

                List<String> leftTemp = sortSub(is, 0, k, true);

                List<String> rightTemp = sortSub(is, k, is.length, false);

                int lengthTemp = (leftTemp.size()==0 ? 0 : leftTemp.get(0).split(" ").length)

                        + (rightTemp.size()==0 ? 0 : rightTemp.get(0).split(" ").length);

                if (lengthTemp > length) {

                    length = lengthTemp;

                    result = new HashSet<>();

                    if (leftTemp.size()==0) {

                        result.addAll(rightTemp);

                    }

                    for (String lStr : leftTemp) {

                        for (String rStr : rightTemp) {

                            result.add(lStr + " " + rStr);

                        }

                    }

                } else if (lengthTemp == length) {

                    if (leftTemp.size()==0) {

                        result.addAll(rightTemp);

                    }

                    for (String lStr : leftTemp) {

                        for (String rStr : rightTemp) {

                            result.add(lStr + " " + rStr);

                        }

                    }

                }

            }



            Object[] strings = result.toArray();

            Arrays.sort(strings);

            for (Object o : strings) {

                System.out.println(o);

            }



        }



    }



    private static int[] duplicateRemoval(int[] is) {

        List<Integer> list = new ArrayList<>();

        list.add(is[0]);

        for (int i = 1; i < is.length; i++) {

            if (is[i] != is[i - 1]) {

                list.add(is[i]);

            }

        }



        return list.stream().mapToInt(Integer::valueOf).toArray();

    }



    private static List<String> sortSub(int[] is, int begin, int end, boolean up) {

        List<String>[] lisTrace = new List[end - begin];

        int[] lis = new int[end - begin];



        for (int i = 0; i < end - begin; i++) {

            lisTrace[i] = new ArrayList<>();

            lisTrace[i].add(String.valueOf(is[i + begin]));

            lis[i] = 1;

            for (int j = 0; j < i; j++) {

                if ((is[i + begin] > is[j + begin]) == up) {

                    if (lis[j] + 1 > lis[i]) {

                        lis[i] = lis[j] + 1;

                    }

                    if (lis[j] + 1 == lis[i]) {

                        for (String str : lisTrace[j]) {

                            lisTrace[i].add(str + " " + is[i + begin]);

                        }

                    }

                }

            }

            Iterator<String> it = lisTrace[i].iterator();



            while (it.hasNext()) {

                String[] str = it.next().split(" ");

                if (str.length < lis[i]) {

                    it.remove();

                }

            }

        }



        List<String> result = new ArrayList<>();

        int max = max(lis);

        int index = index(lis, max);

        while (index >= 0) {

            result.addAll(lisTrace[index]);

            lis[index] = 0;

            index = index(lis, max);

        }



        return result;

    }

    private static int max(int[] a) {
        int max = 0;
        for (int i : a) {
            if (i > max) {
                max = i;
            }
        }
        return max;
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