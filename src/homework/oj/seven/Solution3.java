package homework.oj.seven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author leolu
 * @create 2019-11-21-12:12
 **/
public class Solution3 {
}
class dmg
{
    //look for minimum value withing range
    public static int getMin(int arr[],int l,int h)
    {
        int res=Integer.MAX_VALUE;
        for(int i=l;i<=h;++i)
            if(arr[i]<res)
                res=arr[i];
        return res;
    }
    public static int minCost(int arr[][],int n)
    {
        int dp[][]=new int[n][3];
        for(int i=0;i<3;++i)
            dp[0][i]=arr[0][i];
        for(int i=1;i<n;++i)
        {
            for(int j=0;j<3;++j){
                dp[i][j]=Math.min(getMin(dp[i-1],0,j-1),getMin(dp[i-1],j+1,2))+arr[i][j];

            }
        }
        int res=Integer.MAX_VALUE;
        for(int i=0;i<3;++i)
            if(dp[n-1][i]<res)
                res=dp[n-1][i];
        return res;

    }
    public static void main (String[] args)
    {
        Scanner ab=new Scanner(System.in);
        int t=ab.nextInt();
        while(t-->0)
        {
            int n=ab.nextInt();
            int arr[][]=new int[n][3];
            for(int i=0;i<n;++i)
                for(int j=0;j<3;++j)
                    arr[i][j]=ab.nextInt();
            System.out.println(minCost(arr,n));
        }
    }
}



class T
{
    public static void main (String[] args)
    {
        int i;
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0)
        {
            int n = in.nextInt();
            ArrayList<Integer> li = new ArrayList<Integer>();
            for (i = 0; i < n; i++)
                li.add(in.nextInt());

            int sum = 0;
            check(li, n, sum);
        }
    }

    static void check(ArrayList<Integer> li,int n, int sum)
    {
        if (li.size() == 0)
        {
            System.out.println(sum);
            return;
        }
        else
        {
            Collections.sort(li);
            int num = li.get(li.size()-1);
            sum = sum + num;
            li.remove(li.size()-1);
            if (li.size() == 0)
            {
                System.out.println(sum);
                return;
            }
            if (li.contains(num-1))
            {
                li.remove(Integer.valueOf(num-1));
            }
            n = li.size();
            check(li, n, sum);
        }
    }
}