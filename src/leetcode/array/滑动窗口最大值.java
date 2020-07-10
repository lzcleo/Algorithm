package leetcode.array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 滑动窗口最大值 {
    //暴力解的做法是枚举出所有窗口，然后，分别判断最大值
    //下面使用单调队列来解
    class MonotonicQueue {
        /**
         * 单调队列, 需要实现
         * 1. 在队尾添加元素n
         * 2. 返回队列最大值
         * 3. 若队首元素是n、删除该元素
         * 3个操作
         */
        private Deque<Integer> data = new ArrayDeque<>(); // 存放数据
        public void push(int n) {
            while (!data.isEmpty() && data.peekLast() < n) {
                data.pollLast();
            }
            data.offerLast(n);
        }

        public int max() {
            return data.peekFirst();
        }

        /**
         * 若队首元素为n、则删除
         */
        public void pop(int n) {
            if (!data.isEmpty() && data.peekFirst() == n) {
                data.pollFirst();
            }
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue win = new MonotonicQueue();
        List<Integer> res = new LinkedList<>();
        for (int i=0; i<nums.length; i++) {
            if (i<k-1) {
                win.push(nums[i]);
            } else {
                win.push(nums[i]); // 先将元素放入
                res.add(win.max());
                win.pop(nums[i-k+1]);
            }
        }
        // 这里是改动的地方
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
