package chase;


import org.junit.Test;

import java.util.*;

/**
 *
 */
public class SolutionTest {
    public static String serialize(TreeNode root) {
        if(root == null)
            return "[]";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder res = new StringBuilder("[");
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            res.append(node.val + ",");
            if (node.left == null) {
                res.append("null,");
            } else {
                queue.offer(node.left);
            }

            if(node.right == null){
                res.append("null,");
            } else {
                queue.offer(node.right);
            }
        }
        String result = res.toString().substring(0, res.length() - 1);
        return result += "]";

    }

    // Decodes your encoded data to tree.
    public int getInt(char a) {
        if (a >= '0' && a <= '9')
            return a - '0';
        return a - 'a' + 10;
    }

    @Test
    public void test() {
        System.out.println(getInt('a'));
        int[] nums= {0,1,2};
        System.out.println(nums.toString());
        System.out.println(Arrays.asList(nums).toString());
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int x) {
        if (stack == null && minStack == null) {
            stack.push(x);
            minStack.push(x);
        } else {
            if (x > minStack.peek()) {
                minStack.push(minStack.peek());
                stack.push(x);
            } else {
                stack.push(x);
                minStack.push(x);
            }
        }
    }

    public void pop() {
        minStack.pop();
        stack.pop();
    }

    public int top() {
        return Integer.valueOf(stack.peek());
    }

    public int getMin() {
        return stack.peek();
    }


}

