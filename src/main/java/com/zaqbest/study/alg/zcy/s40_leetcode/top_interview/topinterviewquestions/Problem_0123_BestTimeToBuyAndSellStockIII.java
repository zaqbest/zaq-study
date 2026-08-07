package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * {@link Problem_0121_BestTimeToBuyAndSellStock}
 * {@link Problem_0122_BestTimeToBuyAndSellStockII}
 * {@link Problem_0123_BestTimeToBuyAndSellStockIII}
 * {@link Problem_0188_BestTimeToBuyAndSellStockIV}
 * {@link Problem_0309_BestTimeToBuyAndSellStockWithCooldown}
 */
public class Problem_0123_BestTimeToBuyAndSellStockIII {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int ans = 0;
		int doneOnceMinusBuyMax = -prices[0];//操作了一次，第二次已经买入，可以获得的最大价值
		int doneOnceMax = 0;// 0 : [0] - [0]，只操作一次能获得的最大值
		int min = prices[0];//当前碰到的小价格
		for (int i = 1; i < prices.length; i++) {
			ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
			min = Math.min(min, prices[i]);
			doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
			doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
		}
		return ans;
	}
}
