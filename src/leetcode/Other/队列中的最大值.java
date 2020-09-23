package leetcode.Other;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author leolu
 * @create 2020-06-30-23:26
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 **/
public class 队列中的最大值 {
    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        int i = maxQueue.pop_front();
        System.out.println(i);
    }
}

class MaxQueue {
    private Deque<Integer> queue = new LinkedList<>();
    private Deque<Integer> max = new LinkedList<>();

    //做这道题出错最多的地方在isEmpty()方法和==null上
    public MaxQueue() {

    }

    public int max_value() {
        if (max.isEmpty()) return -1;
        return max.getFirst();
    }

    public void push_back(int value) {
        queue.offerLast(value);
        while (!max.isEmpty() && max.peekLast() < value) {
            max.pollLast();
        }
        max.offerLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) return -1;
        if (queue.peekFirst().equals(max.peekFirst())) {
            max.pollFirst();
        }
        return queue.pollFirst();
    }
}
