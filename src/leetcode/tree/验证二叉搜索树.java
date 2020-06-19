package leetcode.tree;

import java.util.Stack;

/**
 * @author leolu
 * @create 2020-06-17-22:34
 **/
public class 验证二叉搜索树 {
    /**
     * 自己的解法，感觉树的题目很多都可以这么做，把遍历拿到外面去，然后在遍历过程中进行处理，这样做起来更好理解
     */
    private TreeNode pre;
    private boolean res = true;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        inOrder(root);
        return res;
    }

    public void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        if (pre != null && pre.val >= root.val) {
            res = false;
        }
        pre = root;
        inOrder(root.right);
    }


    //用迭代做，原理相同，但是比递归慢很多
    public boolean isValidBSTSecond(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}
