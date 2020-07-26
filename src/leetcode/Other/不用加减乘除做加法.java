package leetcode.Other;

public class 不用加减乘除做加法 {
    //a ^ b表示没有考虑进位的情况下两数的和，(a & b) << 1就是进位
    //递归会终止的原因是 (a & b) << 1 最右边会多一个 0，那么继续递归，进位最右边的 0 会慢慢增多，最后进位会变为
    //0，递归终止。
    public int add(int a, int b) {
        return b == 0 ? a : add(a ^ b, (a & b) << 1);
    }


    //计算1到n的加法
    private int res;
    public int sumNums(int n) {
        //利用系统栈依次压入n.. 1,需要注意使用一个boolean变量，这样使用短路与编译才不会报错
        boolean index = (n > 1) && (sumNums(n - 1) > 0);
        res += n;
        return res;
    }
}
