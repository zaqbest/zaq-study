package com.zaqbest.study.alg.leetcode;

/**
 * 64；最小路径和
 * 问题描述：https://leetcode-cn.com/problems/minimum-path-sum/
 *
 * 算法描述：最简单的dp问题
 */
public class Problem_0064_MinPathSum {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return -1;

        int m = grid.length;
        int n = grid[0].length;

       int[][] dp = new int[m][n];
       dp[0][0] = grid[0][0];
       //初始化行
       for (int i = 1; i < m; i++){
           dp[i][0] = dp[i-1][0]+grid[i][0];
       }
       //初始化列
        for (int j = 1; j < n; j++){
            dp[0][j] = dp[0][j-1]+grid[0][j];
        }

        //计算
        for(int i = 1; i < m; i++){
            for(int j = 1; j <n ;j++){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }
}
