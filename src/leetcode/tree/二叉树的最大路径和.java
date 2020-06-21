package leetcode.tree;

/**
 * User: leo
 * Date: 2020/6/21
 * Time: 11:18
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 */
public class 二叉树的最大路径和 {
    /**
     * 这道题的解法与路径总和③类似,首先需要找到合适的递归定义,同时由于不一定经过根节点和叶子节点，需要对题目进行拆解
     * 但是在递归的时候对结果进行处理的时候有一个小细节
     */
    private int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        PathSum(root);
        return result;
    }

    public int PathSum(TreeNode root) {
        if (root == null) return 0;
        int leftSum = Math.max(PathSum(root.left), 0);
        int rightSum = Math.max(PathSum(root.right), 0);
        //这里需要同时加上左子树以及右子树能够提供的最大值
        int sum = leftSum + rightSum + root.val;
        result = Math.max(result, sum);
        //但是在return的时候，我们的定义是该节点能够提供的最大值，如果左右子树都选上，那样就不是一条路径了，所以return的时候要特别注意
        //若是直接return sum，测试[5,4,8,11,null,13,4,7,2,null,null,null,1]这个用例时，输出55，但是正确输出为48
        return root.val + Math.max(leftSum, rightSum);
    }
}
