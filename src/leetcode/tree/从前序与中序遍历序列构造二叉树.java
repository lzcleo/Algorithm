package leetcode.tree;

import java.util.HashMap;

/**
 * User: leo
 * Date: 2020/6/20
 * Time: 21:08
 * 这题没啥好说的，递归就完事了
 */
public class 从前序与中序遍历序列构造二叉树 {
    private HashMap<Integer,Integer> index = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i ++) {
            index.put(inorder[i], i);
        }
        return generateTree(preorder, 0, preorder.length - 1, 0);
    }

    private TreeNode generateTree(int[] preorder, int prel, int prer, int inl) {
        if (prel > prer) return null;
        TreeNode root = new TreeNode(preorder[prel]);
        int in = index.get(root.val);
        int leftTreeSize = in - inl;
        root.left = generateTree(preorder, prel + 1, prel + leftTreeSize, inl);
//        root.right = generateTree(preorder, prel + leftTreeSize + 1, prel, in + 1); 粗心了，这里应该严格按照定义，应该是prer
        root.right = generateTree(preorder, prel + leftTreeSize + 1, prer, in + 1);
        return root;
    }
}
