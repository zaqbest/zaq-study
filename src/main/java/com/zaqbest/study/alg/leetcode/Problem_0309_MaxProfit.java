package com.zaqbest.study.alg.leetcode;

/**
 * 309. 最佳买卖股票时机含冷冻期
 *
 * 暴力递归+dp优化（记忆化搜索）
 */
public class Problem_0309_MaxProfit {
    public static void main(String[] args) {
        Problem_0309_MaxProfit solution = new Problem_0309_MaxProfit();
        int m = solution.maxProfit(new int[]{1,2,3,0,2});
        System.out.println(m);
    }

    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length+1][prices.length];
        for(int i = 0; i < prices.length;i++)
            for (int j = 0; j < prices.length; j++)
                dp[i][j] = -1;
        return helper(prices, prices.length, -1, 0,dp);
    }

    /**
     *
     * @param prices
     * @param N
     * @param buyIndex 买入点的索引,范围-1...N-2
     * @param curIndex 当前处理节点，0-curIndex-1之前的都已经决定了,取数范围0。。。N-1
     * @return 最大收益
     */
    int helper(int[] prices, int N, int buyIndex, int curIndex, int[][] dp){

        int maxProfit = 0;
        if (curIndex > N -1){
            return 0;
        }

        if (dp[buyIndex+1][curIndex] != -1)
            return dp[buyIndex+1][curIndex];

        else if (curIndex == N -1) {
            //最后一天，如果当前是买入状态，则必须卖出
            if (buyIndex != -1){
                maxProfit = prices[curIndex] - prices[buyIndex];
                dp[buyIndex+1][curIndex] = maxProfit;
                return maxProfit;
            }
        }

        //当前为买入的状态
        if (buyIndex == -1){
            //可以买入
            int profit1 = helper(prices, N, curIndex, curIndex+1, dp);
            maxProfit = Math.max(maxProfit, profit1);
            //可以不操作
            int profit2 = helper(prices, N, buyIndex, curIndex+1, dp);
            maxProfit = Math.max(maxProfit, profit2);
        } else {
            //可以选择不操作
            int profit3 = helper(prices, N, buyIndex, curIndex+1, dp);
            maxProfit = Math.max(maxProfit, profit3);
            //可以选择卖出
            int profit4 = prices[curIndex]-prices[buyIndex] + helper(prices, N, -1, curIndex+2, dp);
            maxProfit = Math.max(maxProfit, profit4);
        }
        dp[buyIndex+1][curIndex] = maxProfit;
        return maxProfit;
    }
}
