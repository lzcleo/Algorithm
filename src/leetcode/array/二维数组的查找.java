package leetcode.array;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * @author leolu
 * @create 2020-04-11-17:25
 * 简单题 但是因为参数定义不明确 写错了
 **/
public class 二维数组的查找 {
    public boolean findNumberIn2DArrayFalse(int[][] matrix, int target) {//没有注意边界输入
        int i = matrix[0].length - 1, j = 0; //从这里开始没有把握住自己定义的行和列
        while ( i >= 0 && j < matrix[0].length) { //j < matrix.length
            if (target > matrix[i][j]) {
                i --;
            } else if (target < matrix[i][j]) {
                j ++;
            } else {
                return true;
            }
        }
        return false;
    }
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int i = matrix[0].length - 1, j = 0;
        while ( i >= 0 && j < matrix.length) {
            if (target > matrix[j][i]) {
                j ++;
            } else if (target < matrix[j][i]) {
                i --;
            } else {
                return true;
            }
        }
        return false;
    }
}
