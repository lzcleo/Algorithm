package homework.oj.five;


import java.util.Scanner;

/**
 * @author leolu
 * @create 2019-10-31-18:58
 **/
public class Solution2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        for (int i = 0; i < times; i++) {
            sc.nextLine();
            int k =sc.nextInt();
            int j =2;
            while (j < k){
                int m =k-j;
                if (isPrimeNormal(j) && isPrimeNormal(m)){
                    System.out.println(j + " "+ m);
                    break;
                }
                j++;
            }
        }
    }


    public static boolean isPrimeNormal(int num) {
        for(int i=2; i<num; i++) {
            if(num%i == 0) {
                return false;
            }
        }

        return true;
    }
}

