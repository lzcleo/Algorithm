package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leolu
 * @create 2020-03-18-18:53
 **/
public class 路径总和 {
    /**
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和
     * @param root
     * @param sum
     * @return
     * 这道题最难的点就在于判断递归头，考虑 5,null,8,13,4这棵二叉树，根节点为5，若sum为5，且以注释中代码做递归头，则，5->null符合
     * return true，但是不符合题意，因为题意需要 “根节点到叶子节点的路径 ”
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == sum;
//        if (root == null)
//            return sum == 0;
        boolean leftHasPathSum = hasPathSum(root.left, sum - root.val);
        boolean rightHasPathSum = hasPathSum(root.right, sum - root.val);
        return (leftHasPathSum || rightHasPathSum);
    }

}
class 路径总和2{
    /**
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     * @param root
     * @param sum
     * @return
     * 未接之谜，这题我错在哪？？
     * 找到错在哪了...不该有那个return，这道题目的思路和回溯几乎一模一样
     * 但是这道题的错误还是不太理解
     * 不加return 输入 [5,4,8,11,null,13,4,7,2,null,null,5,1] 22
     *            输出 [[5,4,11,2],[5,4,8,4,5]]
     *            正确输出 [[5,4,11,2],[5,8,4,5]]
     */
    private List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        if (root == null)
            return res;
        pathSumHelper(root, sum, new ArrayList<>());
        return res;
    }
    private void pathSumHelper(TreeNode root, int sum, ArrayList<Integer> list) {
        if (root == null)
            return;
        list.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            res.add(new ArrayList<>(list));
//            return ;
        }
        pathSumHelper(root.left, sum - root.val, list);
        pathSumHelper(root.right, sum - root.val, list);
        list.remove(list.size() - 1);
    }

}

class 路径之和3{
    /**
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     *
     * 找出路径和等于给定数值的路径总数。
     *
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     */

    //以root为根节点的二叉树中，寻找和为sum的路径，返回这样的路径个数
    public int pathSumNumber(TreeNode root, int sum) {
        if (root == null)
            return 0;
        int res = findPath(root, sum);
//        res += findPath(root.left, sum);
//        res += findPath(root.right, sum);
        res += pathSumNumber(root.left, sum);
        res += pathSumNumber(root.right, sum);
        return res;
    }

    //寻找以node为根节点切包含node的路径数
    private int findPath(TreeNode node, int num) {
        if (node == null)
            return 0;
        int res = 0;
        if (node.val == num) {
            res += 1;
            //此处是这道题的一个难点，因为尽管可能已经出现了和为num的路径，
            // 但是后面还有可能出现和为0的子路径，与此路径产生新路径，故此不能直接return
        }

        res += findPath(node.left, num - node.val);
        res += findPath(node.right, num - node.val);

        return res;

    }

    /**
     * 另一种解法 要快很多
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        return pathSum(root, sum, new int[1000], 0);
    }

    public int pathSum(TreeNode root, int sum, int[] array/*保存路径*/, int p/*指向路径终点*/) {
        if (root == null) {
            return 0;
        }
        int tmp = root.val;
        int n = root.val == sum ? 1 : 0;
        for (int i = p - 1; i >= 0; i--) {
            tmp += array[i];
            if (tmp == sum) {
                n++;
            }
        }
        array[p] = root.val;
        int n1 = pathSum(root.left, sum, array, p + 1);
        int n2 = pathSum(root.right, sum, array, p + 1);
        return n + n1 + n2;
    }

}