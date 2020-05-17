package homework.oj.five;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author leolu
 * @create 2019-10-31-19:22
 **/
public class Solution1 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        getNumByDivisor(sc);
    }

    public static void getNumByDivisor(Scanner sc)
    {
        int testNum = sc.nextInt();
        while (testNum-- > 0)
        {
            long num = sc.nextLong();
            long max = (num / 17) * 17;
            Set<String> set = RollList(String.valueOf(num), 0);
            Iterator<String> it = set.iterator();
            while (it.hasNext())
            {
                long value = Long.parseLong(it.next());
                if (value % 17 == 0 && value > max)
                {
                    max = value;
                }
            }
            if (max == 0)
            {
                System.out.println("Not Possible");
            }
            else
            {
                System.out.println(max);
            }
        }
    }

    public static Set<String> RollList(String str, int i)
    {
        Set<String> set = new HashSet<>();
        if (i >= (str.length()-1))
        {
            set.add(str);
            return set;
        }
        char[] charArray = str.toCharArray();
        String now = "";
        for (int k = i; k < str.length(); k++)
        {
            char temp = charArray[i];
            charArray[i] = charArray[k];
            charArray[k] = temp;
            now = String.valueOf(charArray);
            set.addAll(RollList(now,(i+1)));
            temp = charArray[i];
            charArray[i] = charArray[k];
            charArray[k] = temp;
        }
        return set;
    }
}


