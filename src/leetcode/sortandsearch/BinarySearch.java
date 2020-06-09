package leetcode.sortandsearch;

/**
 * @author leolu
 * @create 2020-04-03-11:04
 **/
public class BinarySearch {
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

}
