package leetcode.tree;

/**
 * User: leo
 * Date: 2020/6/14
 * Time: 14:08
 */
public class 平衡二叉树 {
    /**
     * 这是第一种解法，第一时间想到的也是这种解法，由顶向下，逐个判断子树的高度，但是没有同时判断左右子树是否平衡，故错误
     * @param root
     * @return
     * 时间复杂度，最差情况为满二叉树的时候，节点数为N，此时层数为logN，通过调用 depth(root) ，判断二叉树各层的节点的对应子树的深度，每层执行复杂度为N，故总复杂度为O(NlogN)
     * 空间复杂度在退化成链表时递归需要O(N)的栈空间
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
//        return Math.abs(depth(root.left) - depth(root.right)) <= 1 ;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    /**
     * 另一种写法
     */
    private boolean isBalanced = true;

    public boolean IsBalanced_Solution(TreeNode root) {
        height(root);
        return isBalanced;
    }

    private int height(TreeNode root) {
        if (root == null || !isBalanced)
            return 0;
        int left = height(root.left);
        int right = height(root.right);
        if (Math.abs(left - right) > 1)
            isBalanced = false;
        return 1 + Math.max(left, right);
    }

    /**
     * 后序遍历+剪枝
     * @param root
     * @return
     * 时间复杂度 O(N)： N 为树的节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度 O(N)： 最差情况下（树退化为链表时），系统递归需要使用 O(N) 的栈空间。
     */
    public boolean isBalancedSecond(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) return 0;
        int left = recur(root.left);
        if(left == -1) return -1;
        int right = recur(root.right);
        if(right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

}
