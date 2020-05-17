package homework.oj.third;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author shkstart
 * @create 2019-10-17-19:20
 **/
public class Solution2 {
    public static  void  main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nextInt = scanner.nextInt();
        while (nextInt > 0) {
            BigInteger num = scanner.nextBigInteger().multiply(BigInteger.valueOf(6));
            int i =1;
            while (true){
                BigInteger bigNumber = BigInteger.valueOf(i* (i + 1L)).multiply(BigInteger.valueOf(2*i + 1));
                if(num.compareTo(bigNumber)<0){
                    break;
                }

                else {
                    i+=1;
                }
            }
            System.out.println(i-1);
            nextInt--;
        }
    }

}

