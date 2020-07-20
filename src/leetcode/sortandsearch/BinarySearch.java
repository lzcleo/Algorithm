package leetcode.sortandsearch;

import java.util.PriorityQueue;

/**
 * @author leolu
 * @create 2020-04-03-11:04
 **/
public class BinarySearch {
    private int binarySearchSecond(int[] nums, int n, int target) {
//          此时定义为在[l,r)中寻找target，区间前闭后开
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] < target)
                l = mid + 1;
            if (nums[mid] > target)
                r = mid;
        }
        return -1;
    }


    //还需要注意其他变式,下面是搜索左边界
    private static int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意
        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意 }
            }
            return left;
        }
        return nums[left] == target ? left : -1;
    }

    //搜索右边界
    private int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left - 1; // 注意
    }

    private int binarySearch(int[] nums, int n, int target) {
//          此时定义为在[l,r]中寻找target，区间前闭后闭
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] < target)
                l = mid + 1;
            if (nums[mid] > target)
                r = mid - 1;
        }
        return -1;
    }

    //搜索左边界，但是需要注意这个时候的循环结束条件
    static int left_boundS(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
            // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
            // 最后要检查 left 越界的情况
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }

    //搜索右边界，但是需要注意这个时候的循环结束条件
    int right_boundS(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;
            }
        }
        // 最后要检查 right 越界的情况
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }

}