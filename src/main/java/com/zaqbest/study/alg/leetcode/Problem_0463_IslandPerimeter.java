package com.zaqbest.study.alg.leetcode;

public class Problem_0463_IslandPerimeter {
    public static int islandPerimeter(int[][] grid) {
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if (grid[r][c] == 1){
                    return dfs(grid, r, c);
                }
            }
        }

        return 0;
    }

    public static int dfs(int[][] grid, int r, int c){
        //如果出界了，边长增加1
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length){
            return 1;
        }

        //到达水域，周长增加1
        if (grid[r][c] == 0){
            return 1;
        }

        //已经来过该区域了，不需要增加
        if (grid[r][c] == 2){
            return 0;
        }

        //设置为2，表示该点已经被访问过了
        grid[r][c] = 2;

        return dfs(grid, r-1, c)
                + dfs(grid, r+1, c)
                + dfs(grid, r, c-1)
                + dfs(grid, r, c+1);

    }
}
