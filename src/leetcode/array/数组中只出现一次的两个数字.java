package leetcode.array;

/**
 * @author leolu
 * @create 2020-04-06-16:02
 * 转化为位运算：
 * 向下整除 n//2 等价于 右移一位 n >> 1 ；
 * 取余数n%2 等价于 判断二进制最右一位值 n&1 ；
 *
 **/
public class 数组中只出现一次的两个数字 {
    public int[] singleNumber(int[] nums) {
        /**
         *  方式一：利用hash记录个数，但是空间复制度不满足，或者排序但是时间复杂度度不满足
         *  方式二：异或 + &  如果异或还不太懂的可以先看下先关的几个题目先把异或搞懂先
         *          第一步：所有数异或，所有数异或之后的值就是两个只出现一次的数a,b异或后的值s。
         *          第二步：那我们用s & (-s) 可以得出s最低位(也就是最右边)为1的bit位(这个操作不太会事先知道)对应的数k，
         *                  这个为1的bit位肯定只来之a.b其中的一个数字中的对应二进制位的1---这句话是最关键的点
         *                  这里有点卡住理解，比如我们把数字转成二进制运算看下：
         *                  a：1 -> 0001
         *                  b：2 -> 0010  => 0011. 不全为1的bit为进行异或操作就为1，这是异或的基本流程，
         *                  然后我们操作s & (-s)之后得到的是0001，可以看到这个1是来之数字a的
         *                  （既然能得到这个1，说明a和b在这一位上一定是不一样的，所以下一步可以用&操作分组）
         *          第三步：我们得到s&(-s)之后在对所有数进行&操作的话，就意味着可以将a和b区分在0和1的两个组，
         *                  至于其他的数字如果相同的数字自然会分到一个组，可以用纸笔写下整个过程
         *          第四步：经过第三步之后就变成了只有一个数字存在一个其他都存在两个的数组(也就变成leetcode136. 只出现一次的数字)，
         *                  然后我们分别对两个组的所有数字再进行异或之后不就得到了要求的那两个数了嘛。
         */
        int s = 0;
        for (int num : nums){ // 第一步
            s ^= num;
        }

        int k = s & (-s); // 第二步

        int[] result = new int[2];
        for (int num : nums){
            if ((num & k) == 0){ // 第三第四步
                result[0] ^= num;
            }else{
                result[1] ^= num;
            }
        }

        return result;

    }
}
