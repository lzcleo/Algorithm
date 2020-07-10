package leetcode.Dyp;

public class 不同的二叉搜索树 {
    //https://leetcode-cn.com/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode/
    /**
     * 我们定义G(N)：长度为n的序列的不同二叉搜索树个数
     *        F(i,n) : 以i为根的不同二叉搜索树个数(1 <= i <= n)
     * 所以，对于G(N)是遍历所有F(i,n)之和，F(3,7)，以 3 为根的不同二叉搜索树个数。为了以 3 为根从序列 [1, 2, 3, 4, 5, 6, 7] 构建二叉搜索树，
     * 我们需要从左子序列 [1, 2] 构建左子树，从右子序列 [4, 5, 6, 7] 构建右子树，然后将它们组合(即笛卡尔积)。
     * 巧妙之处在于，我们可以将 [1,2] 构建不同左子树的数量表示为 G(2), 从 [4, 5, 6, 7]` 构建不同右子树的数量表示为 G(4)。
     * 这是由于 G(n) 和序列的内容无关，只和序列的长度有关。于是，F(3,7) = G(2)⋅G(4)。
     *
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    //G(n)其实是卡特兰数
    public int numTreesSecond(int n) {
        // Note: we should use long here instead of int, otherwise overflow
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }

}
