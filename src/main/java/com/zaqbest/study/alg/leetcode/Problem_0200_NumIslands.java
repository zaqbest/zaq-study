package com.zaqbest.study.alg.leetcode;

/**
 * 200：岛屿数量
 *
 * 算法描述：这个真NB
 * https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
 */
public class Problem_0200_NumIslands {
    public int numIslands(char[][] grid) {
        int res = 0;
        for(int i = 0; i < grid.length;i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == '1'){
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 深度优先搜索
     *
     * @param grid
     * @param r row
     * @param c column
     */
    void dfs(char[][] grid, int r, int c){
        // 判断 base case
        // 如果r,c超过了范围，直接返回
        if (!inArea(grid, r, c)){
            return;
        }

        //如果这个格子不是岛屿，直接返回
        if (grid[r][c] != '1')
            return;

        //访问之后节点变成2
        grid[r][c] = 2;

        //访问上下左右四个方向
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);

    }

    //判断是否越界
    private boolean inArea(char[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }
}
