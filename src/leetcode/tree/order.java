package leetcode.tree;

import java.util.Stack;

/**
 * @author leolu
 * @create 2020-03-01-13:24
 * 非递归实现树的遍历
 *
 **/
public class order {
    public void preOrder(TreeNode head) {
        System.out.println("pre-order:");
        if(head != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(head);
            while(!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.val + " ");
                if (head.right != null)
                    stack.push(head.right);
                if (head.left != null)
                    stack.push(head.left);
            }
        }
    }

    public static void inOrderTraverse(TreeNode head) {
        System.out.println("in-order:");
        if(head != null) {
            Stack<TreeNode> stack = new Stack<>();
            while(!stack.isEmpty() || head != null) {
                if(head != null) {
                    // 当前节点不为空, 将自己压进栈并将自己的左孩子作为当前节点（压入左边界）
                    stack.push(head);
                    head = head.left;
                }else {
                    // 当前节点为空（没有左孩子了）, 将栈顶元素弹出作为当前节点, 并将当前节点的右孩子压进栈
                    head = stack.pop();
                    System.out.print(head.val + " ");
                    head = head.right;
                }
            }
        }
    }

    public static void posOrderTraverse(TreeNode head) {
        System.out.println("pos-order");
        if(head != null) {
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();     // 辅助栈，存储 根 -> 右 -> 左 的结果
            stack1.push(head);
            while(!stack1.isEmpty()) {
                head = stack1.pop();
                stack2.push(head);
                // 有左孩子就先压入左孩子
                if(head.left != null)
                    stack1.push(head.left);
                // 有右孩子就后压入右孩子
                if(head.right != null)
                    stack1.push(head.right);
            }
            // 逆序打印 根 -> 右 -> 左 的结果，就是后序遍历的结果
            while(!stack2.isEmpty())
                System.out.print(stack2.pop().val + " ");
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }
    }
}

