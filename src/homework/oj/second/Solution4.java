package homework.oj.second;

import java.util.Scanner;

/**
 * @author shkstart
 * @create 2019-10-13-15:58
 **/
public class Solution4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        for (int i = 0; i < times; i++) {
            sc.nextLine();
            int j = sc.nextInt();
            System.out.println(caculate(j));
        }

    }


    private static int caculate(int i){
        if (1 == i){
            return 2;
        }else {
            return caculate(i-1)*3+2;
        }
    }

}
