package chase.problem49;

import org.junit.Test;

/**
 * @author leolu
 * @create 2020-02-05-17:56
 * 丑数
 *
 **/
public class Solution {
    public int getUglyNumber(int index) {
        if (index <= 0)
            return 0;
        int[] uglyNumber = new int[index + 1];
        int i2 = 0 ,i3 = 0,i5 = 0;
        for (int i = 1; i <= index; i++) {
            int next2 = i2 * 2;
            int next3 = i3 * 3;
            int next5 = i2 * 5;
            uglyNumber[i] = Math.min(next2,Math.min(next3,next5));
            if (next2 == uglyNumber[i])
                i2 ++;
            if (next3 == uglyNumber[i])
                i3 ++;
            if (next5 == uglyNumber[i])
                i5 ++;
        }

        return uglyNumber[index];
    }

    @Test
    public void uglyNumber(){
        System.out.println(getUglyNumber(10));
    }

}
