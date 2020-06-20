package leetcode.array;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * User: leo
 * Date: 2020/6/19
 * Time: 14:12
 */
public class 最小的K个数 {
    /**
     * 下面这种解法，主要巧妙使用了快速排序的partition方法，时间复杂度为O(N),由于直接在数组之上进行操作，空间复杂度为O(1)
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr.length < k || arr.length == 0 || arr == null) return null;
        findKthSmallest(arr, k);
        int[] res = new int[k];
        for (int i = 0; i < k; i ++) {
            res[i] = arr[i];
        }
        return res;
    }

    private void findKthSmallest(int[] arr, int k) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int index = partition(arr, l, r);
            if (index == k) return;
            if (index < k) {
                l = index + 1;
            } else {
                r = index - 1;
            }
        }
    }

    private int partition(int[] nums, int l, int r) {
        int i = l, j = r + 1;
        int temp = nums[l];
        while (true) {
            while(nums[++i] < temp && i < r);
            while(nums[--j] > temp && l < j);
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 唯一注意的是求最小的k个数字需要用最大堆，时间复杂度为O(Nlogk),空间复杂度为 O(k)
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
        if (k > nums.length || k <= 0)
            return new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            maxHeap.add(num);
            if (maxHeap.size() > k)
                maxHeap.poll();
        }
        return new ArrayList<>(maxHeap);
    }
}
