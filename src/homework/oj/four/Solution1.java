package homework.oj.four;

import java.util.Scanner;

/**
 * @author leolu
 * @create 2019-10-27-11:46
 **/
public class Solution1 {


    public static String lcse(String str1, String str2){
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getdp(chs1, chs2);
        int m = chs1.length - 1;
        int n = chs2.length - 1;
        char[] res = new char[dp[m][n]];


        return String.valueOf(res);
    }

    public static int[][] getdp(char[] str1, char[] str2){
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0]= str1[0] == str2[0] ? 1 :0;
        for (int i = 1; i < str1.length;i ++){
            dp[i][0]= Math.max(dp[i-1][0],str1[i]==str2[0] ? 1 :0);
        }
        for (int j = 1; j < str2.length;j ++){
            dp[0][j]= Math.max(dp[0][j-1],str1[0]==str2[j] ? 1 :0);
        }


        return dp;
    }
}
