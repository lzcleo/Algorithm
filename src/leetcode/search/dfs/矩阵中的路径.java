package leetcode.search.dfs;

/**
 * @author leolu
 * @create 2020-04-07-11:34
 * 把一开始写错的逻辑放在下面了，这种题已经相当规范，需要注意的就是递归函数的定义以及边界的处理
 **/
public class 矩阵中的路径 {
    private int[][] route = {{0,1},{1,0},{-1,0},{0,-1}};
    private boolean[][] visited;
    int m,n;
    public boolean exist(char[][] board, String word) {
        if (word.length() == 0 || word == null) {
            return false;
        }
        char[] ch = word.toCharArray();
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < n; j ++ ){
                if (dfs(board, i, j, 0, ch)) return true;
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, int x, int y, int index, char[] ch) {
        if (index == ch.length - 1) {
            return ch[index] == board[x][y];
        }
        if (board[x][y] == ch[index]) {
            visited[x][y] = true;
            for (int[] rt : route) {
                int newx = x + rt[0];
                int newy = y + rt[1];
                if (newx >= 0 && newx < m && newy >= 0 && newy < n && !visited[newx][newy] ) {
                    if (dfs(board, newx, newy, index + 1, ch)) return true;
                }
            }
            visited[x][y] = false;
        }
        return false;
    }
//    private boolean dfs(char[][] board, int x, int y, int index, char[] ch) {
//        if (index == ch.length - 1) return true;  比较应该放在这个地方
//            符合字符相等的条件才走下面的逻辑
//        for (int[] rt : route) {
//            int newx = x + rt[0];
//            int newy = y + rt[1];
//            if (newx >= 0 && newx < m && newy >= 0 && newy < n && !visited[newx][newy] && board[newx][newy] == ch[index + 1]) {
//                visited[newx][newy] = true;
//                dfs(board, newx, newy, index + 1, ch);
//                visited[newx][newy] = false;
//            }
//            continue ;
//        }
//        return false;
//    }
}
