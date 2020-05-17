package leetcode.tree;

/**
 *  输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *  B是A的子结构， 即 A中有出现和B相同的结构和节点值
 *
 * @author leolu
 * @create 2020-04-11-16:03
 *
 * 这道题与路径总和第三种变式有异曲同工之妙，遇到这种可以不从根节点进行计数的题，我们通常需要分为两部分来做
 **/
public class 树的子结构 {
    /**
     * 以下是我写的 只有一个用例没有过
     * 输入 [2,3,2,1] [1]
     * 输出 false
     * 预期 true
     * @param A
     * @param B
     * @return
     */
//     public boolean isSubStructure(TreeNode A, TreeNode B) {
//         if (B == null) return false;
//         if (A == null) return true;
//         return A.val == B.val ? isSubStructureWithRoot(A, B) : isSubStructureWithRoot(A.left, B) || isSubStructureWithRoot(A.right, B);
//     }

    //这样改动之后通过 问题出在我应该不在主函数中判断AB节点的值
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (isSubStructureWithRoot(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }
     private boolean isSubStructureWithRoot(TreeNode a, TreeNode b) {
         if (b == null) return true;
         if (a == null || a.val != b.val) return false;
         return (a.val == b.val && isSubStructureWithRoot(a.left, b.left) && isSubStructureWithRoot(a.right, b.right));
     }


    /**
     * 以下是题解中大佬写的
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructureSecond(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructureSecond(A.left, B) || isSubStructureSecond(A.right, B));
    }
    boolean recur(TreeNode A, TreeNode B) {
        if(B == null) return true;
        if(A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}
