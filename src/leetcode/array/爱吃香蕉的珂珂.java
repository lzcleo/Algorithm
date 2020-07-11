package leetcode.array;

//珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
// 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后
//这一小时内不会再吃更多的香蕉。
// 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
// 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。

// 示例 1：
// 输入: piles = [3,6,7,11], H = 8
//输出: 4
// 示例 2：
// 输入: piles = [30,11,23,4,20], H = 5
//输出: 30
public class 爱吃香蕉的珂珂 {
    /**
     * 我们定义一个canFinish函数判断是否某速度speed能够在H小时内吃完香蕉
     */
    public int minEatingSpeed(int[] piles, int H) {
        if (piles == null || piles.length == 0 || H < piles.length) return -1;
        //注意这个地方，要有这样的敏感，要是出现线性查找，就可以使用二分
        int l = 1, r = getMax(piles);
        while (l <= r) {
            int mid = ((r - l) >> 1) + l;
            if (canFinish(piles, mid, H)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean canFinish(int[] piles, int speed, int H) {
        int time = 0;
        for (int n : piles) {
            time += timeOf(n, speed);
        }
        return time <= H;
    }

    private int timeOf(int n, int speed) {
        return (n / speed) + ((n % speed > 0) ? 1 : 0);
    }

    private int getMax(int[] piles) {
        int max = 0;
        for (int n : piles)
            max = Math.max(n, max);
        return max;
    }

}
