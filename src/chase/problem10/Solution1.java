package chase.problem10;


/**
 * @author shkstart
 * @create 2019-10-15-16:42
 *
 * 求斐波那契数列的第N项
 **/
public class Solution1 {
    public static void main(String[] args) {
        long i =new Solution1().fibonacciOne(10);
        long j =new Solution1().fibonacciTwo(5);
        System.out.println(i);
        System.out.println(j);
    }


   public long fibonacciOne(int n){
        if ( 0 >= n) {
            return  0;
        }
        if (1 == n) {
            return 1;
        }
        return fibonacciOne(n-1)+fibonacciOne(n-2);
    }


    public long fibonacciTwo(int n){
        int[] result = {0,1};
        if (n < 2) {
            return result[n];
        }
        long fibMinusOne = 1;
        long fibMinusTwo = 0;
        long fibN = 0;
        for (int i = 2; i <= n; i++) {
            fibN = fibMinusOne + fibMinusTwo;

            fibMinusTwo = fibMinusOne;
            fibMinusOne = fibN;
        }

        return fibN;
    }

}

