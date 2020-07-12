package leetcode.array;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @author leolu
 * @create 2020-04-11-17:47
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 **/
public class 接雨水 {
    //暴力解法
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int len = height.length, res = 0;
        for (int i = 0; i < len; i++) {
            int lmax = 0, rmax = 0;
            for (int j = i; j < len; j++) {
                rmax = Math.max(height[j], rmax);
            }
            for (int j = i; j >= 0; j--) {
                lmax = Math.max(height[j], lmax);
            }
            res += Math.min(lmax, rmax) - height[i];
        }
        return res;
    }

    //优化过后
    public int trapSecond(int[] height) {
        if (height == null || height.length == 0) return 0;
        int res = 0, left = 0, right = height.length - 1, lmax = 0, rmax = 0;
        while (left <= right) {
            lmax = Math.max(lmax, height[left]);
            rmax = Math.max(rmax, height[right]);
            if (lmax < rmax) {
                res += lmax - height[left];
                left++;
            } else {
                res += rmax - height[right];
                right--;
            }
        }
        return res;
    }

}