package homework.ojclass.str;

import java.util.*;

/**
 * @author leolu
 * @create 2019-11-05-17:49
 *
 * 给定两个字符串，返回两个字符串的最长公共子序列，可能是多个
 *
 * 输入
 * 第一行为用例个数，每个测试用例输入为两行，一行一个字符串
 * 1
 * 1A2BD3G4H56JK
 * 23EFG4I5J6K7
 *
 * 输出
 * 如果没有公共子序列，不输出，如果有多个字典序排序
 * 23G456K
 * 23G45JK
 *
 *
 **/
public class 最长公共子序列 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < times; i++) {

            String s1 = scanner.nextLine();
            String s2 = scanner.nextLine();

            int[][] dp = new int[s1.length() + 1][s2.length() + 1];

            for (int j = 1; j <= s1.length(); j++) {
                for (int k = 1; k <= s2.length(); k++) {
                    if (s1.charAt(j - 1) == s2.charAt(k - 1)) {
                        dp[j][k] = dp[j - 1][k - 1] + 1;
                    } else {
                        dp[j][k] = Math.max(dp[j - 1][k], dp[j][k - 1]);
                    }
                }
            }
            Set<String> traces = new HashSet<>();
            traces.add("");
            int col = 0;
            for (int m = 1; m <= dp[s1.length()][s2.length()]; m++) {
                List<Integer> temp = findJ(dp, col, m);
                col = temp.get(0);
                Set<String> traces2 = new HashSet<>();
                for (int i1 : temp) {
                    for (String trace : traces) {
                        traces2.add(trace + s1.charAt(i1));
                    }
                }
                traces = traces2;
            }

            traces.removeIf(trace -> !isSub(s1, trace) || !isSub(s2, trace)|| trace.length() == 0);
            List<String> list = new ArrayList<>(traces);
            Collections.sort(list);
            for (String s : list) {
                System.out.println(s);
            }
        }
    }

    private static List<Integer> findJ(int[][] dp, int j, int target) {
        List<Integer> result = new ArrayList<>();
        for (int i = j; i < dp.length; i++) {
            for (int m = 1; m < dp[i].length; m++) {
                if (dp[i][m] == target) {
                    if (dp[i - 1][m] == dp[i][m - 1]) {
                        result.add(i - 1);
                    }
                    break;
                }
            }
        }
        return result;
    }

    private static boolean isSub(String a, String b) {
        for (char c : b.toCharArray()) {
            int i = a.indexOf(c);
            if (i < 0) {
                return false;
            }
            a = a.substring(i + 1);
        }
        return true;
    }


    /**
     * 以下是第二种解法
     * @param sc
     */
    public static void maxPublicSubArray(Scanner sc)
    {
        int testNum = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < testNum; ++i)
        {
            String[] stringA = sc.nextLine().split("");
            String[] stringB = sc.nextLine().split("");

            int[][] count = new int[stringA.length+1][stringB.length+1];
            String[][] result = new String[stringA.length+1][stringB.length+1];

            ArrayList<String> list = new ArrayList<>();
            for (int j = 1; j < count.length; ++j)
            {
                for (int k = 1; k < count[0].length; ++k)
                {
                    if (stringA[j-1].equals(stringB[k-1]))
                    {
                        count[j][k] = count[j-1][k-1] + 1;
                        if (result[j-1][k-1] == null)
                        {
                            result[j-1][k-1] = "";
                        }
                        if (!result[j-1][k-1].contains("/"))
                        {
                            result[j][k] = result[j-1][k-1] + stringA[j-1];
                            if (!list.contains(result[j][k]))
                            {
                                list.add(result[j][k]);
                            }
                        }
                        else
                        {
                            String[] temp = result[j-1][k-1].split("/");
                            result[j][k] = temp[0] + stringA[j-1];
                            if (!list.contains(result[j][k]))
                            {
                                list.add(temp[0] + stringA[j-1]);
                            }
                            for (int l = 1; l < temp.length; ++l)
                            {
                                result[j][k] += "/" + temp[l] + stringA[j-1];
                                if (!list.contains(temp[l] + stringA[j-1]))
                                {
                                    list.add(temp[l] + stringA[j-1]);
                                }
                            }
                        }
                    }
                    else if (count[j-1][k] > count[j][k-1])
                    {
                        if (result[j-1][k] == null)
                        {
                            result[j-1][k] = "";
                        }
                        count[j][k] = count[j-1][k];
                        result[j][k] = result[j-1][k];
                    }
                    else if (count[j-1][k] < count[j][k-1])
                    {
                        if (result[j][k-1] == null)
                        {
                            result[j][k-1] = "";
                        }
                        count[j][k] = count[j][k-1];
                        result[j][k] = result[j][k-1];
                    }
                    else
                    {
                        if (result[j-1][k] == null)
                        {
                            result[j-1][k] = "";
                        }
                        if (result[j][k-1] == null)
                        {
                            result[j][k-1] = "";
                        }
                        count[j][k] = count[j-1][k];
                        if (!result[j - 1][k].equals(result[j][k - 1]))
                        {
                            result[j][k] = result[j-1][k] + "/" + result[j][k-1];
                        }
                        else
                        {
                            result[j][k] = result[j-1][k];
                        }
                    }
                }
            }

            int len = count[stringA.length][stringB.length];

            Collections.sort(list);
            for (String string : list)
            {
                if (string.length() == len)
                {
                    System.out.println(string);
                }
            }

        }
    }
}
