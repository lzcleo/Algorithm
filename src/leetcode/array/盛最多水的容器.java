package leetcode.array;

import java.util.Arrays;

//https://leetcode-cn.com/problems/container-with-most-water/solution/container-with-most-water-shuang-zhi-zhen-fa-yi-do/
public class 盛最多水的容器 {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, res = 0;
        while (l < r) {
            res = height[l] < height[r] ? Math.max(res, (r - l) * height[l ++]) : Math.max(res, (r - l) * height[r --]);
        }
        return res;
    }
}
