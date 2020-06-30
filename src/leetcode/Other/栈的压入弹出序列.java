package leetcode.Other;

import java.util.Deque;
import java.util.LinkedList;

/**
 * User: leo
 * Date: 2020/6/28
 * Time: 11:36
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
 * 序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 */
public class 栈的压入弹出序列 {
    /**
     * 这道题广告二面的时候出过，而且非常可惜的是，思路已经完全正确，采用一个栈进行模拟，但是，对于如何处理结果完善的不够好，在对两个数组进行遍历的时候
     * 没有找到下面的正确写法，总是都是对两个数组的索引进行操作，而且边界判断也不对，故fail
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length == 0 || popped.length == 0 || pushed.length != popped.length)
            return false;
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        for (int num : pushed) {
            stack.offerFirst(num);
            while (!stack.isEmpty() && popped[i] == stack.peek()) {
                stack.pollFirst();
                i ++;
            }
        }
        return stack.isEmpty();
    }
}
