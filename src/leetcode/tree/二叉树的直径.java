package leetcode.tree;

/**
 * User: leo
 * Date: 2020/6/14
 * Time: 20:02
 */
public class 二叉树的直径 {
    /**
     * 这道题与路径之和3有异曲同工的地方，都是有可能不经过根节点的题，第一想法是与那道题的做法一样，以此求出以当前节点为根节点且必须经过根节点的二叉树直径
     * 但是这样存在较多的重复计算，故将结果拉出来设为成员变量，再求深度的同时，把以当前节点为根节点且必须经过根节点的二叉树直径一块求了出来
     */
    private int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        getDepth(root);
        return res;
    }

    private int getDepth (TreeNode root) {
        if (root == null) return 0;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        res = Math.max(left + right, res);
        return Math.max(left, right) + 1;
    }
}
