package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 二叉树的完全性检验 {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode left = null;
        TreeNode right = null;
        boolean isMeetLeaf = false;//代表是否遇到节点不双全的节点
        while (!queue.isEmpty()) {
            root = queue.poll();
            left = root.left;
            right = root.right;
            //遇到左右孩子不双全的节点并且该节点不是叶子节点的时候就不是完全二叉树
            if ((isMeetLeaf && !(left == null && right == null)) || (left == null && right != null)
                //左孩子为空并且右孩子不为空的时候不是完全二叉树
            ) {
                return false;
            }
            if (left != null) queue.offer(left);
            if (right != null) queue.offer(right);
            if (left == null || right == null) isMeetLeaf = true;
        }
        return true;
    }
}
