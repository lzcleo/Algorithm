package leetcode.tree;

/**
 * User: leo
 * Date: 2020/6/20
 * Time: 21:10
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * 输入：[5,2,13]
 * 输出：[18,20,13]
 */
public class 把二叉搜索树转换为累加树 {
    private int pre;
    //注意，与中序遍历并不一样，先右后左
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        if (pre == 0) {
            pre = root.val;
        } else {
            root.val += pre;
            pre = root.val;
        }
        convertBST(root.left);
        return root;
    }
}
