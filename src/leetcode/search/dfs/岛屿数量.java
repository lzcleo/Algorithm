package leetcode.search.dfs;


/**
 * @author leolu
 * @create 2020-04-03-11:57
 * 一般下面这种类型的题都是用深度优先搜索来做的，多写几次对代码熟练了就行
 * 但是在与面试官聊的时候可以对其进行改进，在大数据量的情况下，可以用并查集来做，并查集主要使用的是分治的思想
 * 主要需要处理的就是边界的情况，同时在大数据量的时候，这种计算应该是放在spark中进行的
 **/
public class 岛屿数量 {
    private int[][] route = {{-1,0},{0,-1},{1,0},{0,1}};
    private int m, n;
    private int res = 0;
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return -1;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (grid[i][j] == '1') {
                    res += 1;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }
    private void dfs(char[][] grid, int startx, int starty) {
        if (grid[startx][starty] == 0) {
            return ;
        }
        grid[startx][starty] = 0;
        for (int[] i : route) {
            int newx = startx + i[0];
            int newy = starty + i[1];
//            if (inArea(newx, newy) && grid[newx][newy] == 1) 注意是char类型
            if (inArea(newx, newy) && grid[newx][newy] == '1')
                dfs(grid, newx, newy);
        }
        return;
    }
    private boolean inArea(int x, int y) {
        if (x >= 0 && x < m && y >= 0 && y < n)
            return true;
        return false;
    }

}
