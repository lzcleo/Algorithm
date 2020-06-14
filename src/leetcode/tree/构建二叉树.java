package leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * User: leo
 * Date: 2020/6/14
 * Time: 18:07
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 这道题做过几次了，再做还是效果不好，递归的理解还是没有到位
 */
public class 构建二叉树 {
    private Map<Integer, Integer> index = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i ++) {
            index.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length-1, 0);
    }

    private TreeNode buildTree(int[] preorder, int prel, int prer, int inl) {
        if (prel > prer) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[prel]);
        int in = index.get(preorder[prel]);
        int leftTreeSize = in - inl;
        root.left = buildTree(preorder, prel + 1, prel + leftTreeSize, inl);
        root.right = buildTree(preorder, prel + leftTreeSize + 1, prer, in + 1);
        return root;
    }
}
