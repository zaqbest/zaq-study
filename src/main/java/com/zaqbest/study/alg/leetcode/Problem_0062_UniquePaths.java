package com.zaqbest.study.alg.leetcode;

/**
 * 62 不同路径
 * 问题描述：https://leetcode-cn.com/problems/unique-paths/
 *
 *
 */
public class Problem_0062_UniquePaths {

    /**
     * 不同路径
     * 算法描述：
     * 1，对于m*n的矩阵，总的走法为f(m,n)
     * 2.1 第一步可以向下走，剩余的还有m-1行，总的走法为f（m-1,n）
     * 2.2 或者第一步往右走，剩余还有n-1行，总的走法为f(m,n-1)
     * 3，如果只剩下一行或者一列，则只可能有一种走法，f(x,1)=1, f（1，x） =1
     *
     * 存在问题：重复计算，计算超时了
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
       if (m ==1 || n == 1)
           return 1;
       else
           return uniquePaths(m-1, n) + uniquePaths(m, n-1);
    }

    /**
     * 动态规划解法
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePathsV2(int m, int n) {
        if (m ==1 || n == 1)
            return 1;

        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++)
            dp[i][0] = 1;
        for (int j = 1; j < n; j++)
            dp[0][j] = 1;

        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
