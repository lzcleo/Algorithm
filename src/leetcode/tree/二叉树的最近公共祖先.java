package leetcode.tree;

/**
 * @author leolu
 * @create 2020-04-06-10:45
 **/
public class 二叉树的最近公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    /**
     * 以下是不会写的时候从题解中看到的答案..其实很繁琐
     */
    private TreeNode resNode = null;
    public TreeNode lowestCommonAncestorSecond(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        doSelect(root, p, q);
        return resNode;
    }

    /*
       思路：对于树的题目，首先我们要清晰的知道访问树这样的数据结构时，其中一种访问方式就是递归root.left和root.right，不难理解，这是根基
       在递归途中，当前curRoot节点和左右子树的关系，如果当前节点的左右子树只要有一个包含其中一个节点，我们就可以展开来判断了，代码很直观啦！
       当设置了当前节点为结果集的时候，其实就可以给“上层”返回false表示不包含了
     */
    private boolean doSelect(TreeNode curRoot, TreeNode p, TreeNode q){
        if (curRoot == null) return false;
        boolean flag = false; // 用一个局部变量记录当前递归的结果，避免到处return

        boolean leftHas = doSelect(curRoot.left, p, q); // 左子树是否包含p或者q
        boolean rightHas = doSelect(curRoot.right, p, q); // 右子树是否包含p或者q

        if (curRoot.equals(p) || curRoot.equals(q)){ // 如果当前节点就等于其中一个，那我们只要判断左右子树中是否包含另外一个就可以了
            if (leftHas || rightHas){
                resNode = curRoot;
                flag = false;
            }else{ // 如果只有当前节点等于其中一个给“上层”返回true
                flag = true;
            }
        }else if (leftHas && rightHas){ // 如果左右子树都包含的话那么当前节点就是结果
            resNode = curRoot;
            flag = false;
        }else if (leftHas || rightHas){
            flag = true;
        }

        return flag;
    }

}
