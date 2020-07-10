package leetcode.Other;

import java.util.LinkedList;
import java.util.Queue;

public class 队列实现栈 {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        System.out.println(myStack.pop());
        System.out.println(myStack.top());
    }
}
class MyStack {
    private Queue<Integer> a;
    private Queue<Integer> b;
    /** Initialize your data structure here. */
    public MyStack() {
        a = new LinkedList<>();
        b = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        a.offer(x);
        while (!b.isEmpty()) {
            a.offer(b.poll());
        }
        Queue temp = a;
        a = b;
        b = temp;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return b.poll();
    }

    /** Get the top element. */
    public int top() {
        return b.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return b.isEmpty();
    }
}