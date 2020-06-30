package leetcode.array;

/**
 * @author leolu
 * @create 2020-06-30-9:05
 **/
public class 顺时针打印矩阵 {
    public int[] spiralOrder(int[][] matrix) {
        //这道题的难点在于编写代码的细节以及跳出循环条件的判断
        if (matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, u = 0, d = matrix.length - 1, k = 0;
        int[] res = new int[(r + 1) * (d + 1)];
        while (true) {
            for (int i = l; i <= r; i++) res[k++] = matrix[u][i]; // left to right.
            if (++u > d) break;
            for (int i = u; i <= d; i++) res[k++] = matrix[i][r]; // top to bottom.
            if (l > --r) break;
            for (int i = r; i >= l; i--) res[k++] = matrix[d][i]; // right to left.
            if (u > --d) break;
            for (int i = d; i >= u; i--) res[k++] = matrix[i][l]; // bottom to top.
            if (++l > r) break;
        }
        return res;
    }

}
