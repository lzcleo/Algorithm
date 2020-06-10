package leetcode.tree;

import java.util.*;

/**
 * @author leolu
 * @create 2020-03-07-21:21
 * 请实现一个函数按照之字形顺序打印二叉树，
 * 即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 **/
public class 之字形打印二叉树 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
//            Queue<Integer> queue = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        boolean reverse = false;
        queue.add(root);
//            queue.pull(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int levelCount = queue.size();
            for (int i = 0; i < levelCount; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
//               queue.pull(node.left);
//               queue.pull(node.right);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            if (reverse) {
                Collections.reverse(list);
            }
            reverse = !reverse;
            res.add(list);
        }
        return res;
    }

}

/**
 * [3,9,20,null,null,15,7]
 * [[3],[20,9],[15,7],[]]
 * [[3],[20,9],[15,7]]
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}