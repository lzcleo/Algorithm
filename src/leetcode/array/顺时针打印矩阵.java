package leetcode.array;

/**
 * @author leolu
 * @create 2020-06-30-9:05
 **/
public class 顺时针打印矩阵 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return null;
        int l = 0, r = matrix[0].length, u = 0, d = matrix.length, k = 0;
        int[] res = new int[r * d];
        while (true) {
            for (int i = l; i <= r; i ++) {
                res[k ++] =
            }
        }
    }
}
