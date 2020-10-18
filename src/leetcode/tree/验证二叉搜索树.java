package leetcode.tree;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 涉及到树的题目，主要得有以下的思维
 * 1 找到当前节点需要做的事，然后根据需要放到前序 中序 后序对应的位置
 * 2 如果当前节点会对下面的子节点有整体影响，可以通过辅助函数增长参数列表，借助参数传递信息
 * 3 二叉搜索树的做法也会有一些特殊的地方
 **/
public class 验证二叉搜索树 {
    /**
     * 自己的解法，感觉树的题目很多都可以这么做，把遍历拿到外面去，然后在遍历过程中进行处理，这样做起来更好理解
     */
//    private TreeNode pre;
//    private boolean res = true;
//    public boolean isValidBST(TreeNode root) {
//        if (root == null) return true;
//        inOrder(root);
//        return res;
//    }
//
//    public void inOrder(TreeNode root) {
//        if (root == null) return;
//        inOrder(root.left);
//        if (pre != null && pre.val >= root.val) {
//            res = false;
//        }
//        pre = root;
//        inOrder(root.right);
//    }
//
//
//    //用迭代做，原理相同，但是比递归慢很多
//    public boolean isValidBSTSecond(TreeNode root) {
//        Stack<TreeNode> stack = new Stack();
//        double inorder = - Double.MAX_VALUE;
//
//        while (!stack.isEmpty() || root != null) {
//            while (root != null) {
//                stack.push(root);
//                root = root.left;
//            }
//            root = stack.pop();
//            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
//            if (root.val <= inorder) return false;
//            inorder = root.val;
//            root = root.right;
//        }
//        return true;
//    }
//
//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(19);
//        root.left = new TreeNode(11);
//
//    }

    private static TreeNode pre;
    private static boolean res = true;
    private static boolean isBST(TreeNode root) {
        if (root == null)
            return true;
        inorder(root);
        return res;
    }

    private static void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        if (pre != null && pre.val >= root.val)
            res = false;
        pre = root;
        inorder(root.right);
    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
