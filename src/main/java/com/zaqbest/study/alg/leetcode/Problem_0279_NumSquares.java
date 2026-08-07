package com.zaqbest.study.alg.leetcode;

/**
 * 279: 完全平方数
 * https://leetcode-cn.com/problems/perfect-squares/
 *
 * 算法描述
 * https://leetcode-cn.com/problems/perfect-squares/solution/hua-jie-suan-fa-279-wan-quan-ping-fang-shu-by-guan/
 *
 */
public class Problem_0279_NumSquares {
    public int numSquares(int n) {
        if (n == 0) return 0;

        int dp[] = new int[n+1];
        for (int i =1; i <=n; i++){
            dp[i] = i; //最坏的情况 1+1+1

            for (int j = 1; i - j * j >= 0; j++){
                dp[i] = Math.min(dp[i], dp[i - j * j ] + 1);
            }
        }

        return dp[n];
    }
}
