package chase.problem55;

import java.util.*;


/**
 * @author leolu
 * @create 2020-02-10-16:11
 * 二叉树的深度
 * <p>
 * 只出现一次的数字
 **/
public class Solution {
    public static int treeDepth(TreeNode treeNode) {
        if (treeNode == null)
            return 0;
        int left = treeDepth(treeNode.left);
        int right = treeDepth(treeNode.right);
        return (left > right) ? left + 1 : right + 1;
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        if (root != null)
            treeNodeQueue.offer(root);
        while (!treeNodeQueue.isEmpty()){
            TreeNode poll = treeNodeQueue.poll();
            list.add(poll.value);
            if (poll.left != null)
                treeNodeQueue.offer(poll.left);
            if (poll.right != null)
                treeNodeQueue.offer(poll.right);
        }
        return list;
    }

}

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
}
