package com.zaqbest.study.alg.leetcode;

/**
 * 121: 买卖股票的最佳时机
 * 问题描述: https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/submissions/
 */
public class Problem_0121_MaxProfit {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        //遍历prices[i]前，最小值
        int min = Integer.MAX_VALUE;
        int res = 0;

        for (int p: prices){
            min = Math.min(min, p);
            res = Math.max(res, p - min);
        }

        return res;
    }
}
