package chase.problem13;

/**
 * @author leolu
 * @create 2019-10-21-22:16
 *
 * 地上有一个m行n列的方格，机器人从（0,0）的格子开始移动，每次课一想上下左右移动一格，但不能进入行坐标和列坐标的数位之和大于k的格子
 **/
public class Solution1 {

    public static void main(String[] args) {
        int i = new Solution1().movingCount(10, 5, 6);
        System.out.println(i);
    }

    public int movingCount(int threshold,int rows ,int cols){
        if (threshold <0 || rows < 0|| cols < 0) {
            return 0;
        }
        boolean[] visited = new boolean[rows * cols];
        for (int i = 0; i <rows*cols ; i++) {
            visited[i] = false;
        }

        int count = movingCountCore(threshold,rows ,cols,0,0,visited);

        return count;
    }

    public int movingCountCore(int threshold,int rows ,int cols,int row,int col,boolean[] visited){
        int count = 0;

        if (check(threshold,rows,cols,row,col,visited)) {
            visited[row*cols + col] = true;

            count = 1 + movingCountCore(threshold, rows, cols, row-1, col, visited) +
                    movingCountCore(threshold, rows, cols, row, col-1, visited)
                    +movingCountCore(threshold, rows, cols, row+1, col, visited)+
                    movingCountCore(threshold, rows, cols, row, col+1, visited);

        }

        return count;
    }

    public boolean check(int threshold,int rows ,int cols,int row,int col,boolean[] visited){
        if (row >= 0 && col >= 0 && row < rows && col < cols&& getDigitSum(row) + getDigitSum(col) <= threshold && !visited[row*cols + col]) {
            return true;
        }

        return false;

    }


    public int getDigitSum(int number){
        int sum = 0;
        while (number > 0){
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

}
