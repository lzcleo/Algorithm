package chase.problem28;

/**
 * @author leolu
 * @create 2020-01-30-21:33
 * 对称的二叉树
 **/
public class Solution {
    public void Mirror(TreeNode root) {
        if (root == null)
            return;
        TreeNode treeNode = root.left;
        root.left = root.right;
        root.right = treeNode;
        Mirror(root.left);
        Mirror(root.right);
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}