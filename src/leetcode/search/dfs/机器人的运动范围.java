package leetcode.search.dfs;

public class 机器人的运动范围 {
    private final int[][] route = {{-1,0},{0, -1},{1, 0},{0, 1}};
    private int res = 0;
    private int m, n, k;
    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        int[][] path = new int[m][n];
        dfs(path, 0, 0);
        return res;
    }
    private void dfs(int[][] path, int x, int y) {
        if (path[x][y] == 1) return;
        if (!check(x, y)) return;
        res += 1;
        path[x][y] = 1;
        for (int[] rt : route) {
            int newx = x + rt[0];
            int newy = y + rt[1];
            if (check(newx, newy)) dfs(path, newx, newy);
        }
    }
    private boolean check(int x, int y) {
        if (x >= 0 && x < m && y >= 0 && y < n) {
            int x1 = (x / 10) + (x % 10);
            int y1 = (y / 10) + (y % 10);
            return x1 + y1 <= k;
        }
        return false;
    }
}
