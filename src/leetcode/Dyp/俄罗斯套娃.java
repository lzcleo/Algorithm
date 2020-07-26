package leetcode.Dyp;

import java.util.Arrays;

/**
 * @author leolu
 * @create 2020-04-11-17:49
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，
 * 这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 **/
public class 俄罗斯套娃 {
    //最长上升子序列长度
    public int lengthOfLIS(int[] nums) {
        return 0;
    }

    public int maxEnvelopes(int[][] envelopes) {
//        Arrays.sort(envelopes, (int[] arr1, int[] arr2) -> {
//            if (arr1[0] == arr2[0]) {
//                return arr2[1] - arr1[1];
//            } else {
//                return arr1[0] - arr2[0];
//            }
//        });
        Arrays.sort(envelopes, (int[] arr1, int[] arr2) -> arr1[0] == arr2[0] ? arr2[1] - arr1[1] : arr1[0] - arr2[0]);
        // extract the second dimension and run LIS
        int[] secondDim = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; ++i) secondDim[i] = envelopes[i][1];
        return lengthOfLIS(secondDim);
    }
}
