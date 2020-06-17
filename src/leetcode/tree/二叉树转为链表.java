package leetcode.tree;

/**
 * User: leo
 * Date: 2020/6/16
 * Time: 09:54
 */
public class 二叉树转为链表 {
    /**
     * 将左子树插入到右子树的地方
     * 将原来的右子树接到左子树的最右边节点
     * 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     */
    public void flattenFirst(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

    /**
     * 递归的是神，迭代的是人
     */
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        //注意，与后序遍历的顺序不同
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
