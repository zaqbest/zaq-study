package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;
/**
 * {@link Problem_0121_BestTimeToBuyAndSellStock}
 * {@link Problem_0122_BestTimeToBuyAndSellStockII}
 * {@link Problem_0123_BestTimeToBuyAndSellStockIII}
 * {@link Problem_0188_BestTimeToBuyAndSellStockIV}
 * {@link Problem_0309_BestTimeToBuyAndSellStockWithCooldown}
 */
public class Problem_0121_BestTimeToBuyAndSellStock {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		// 0...i 最小值
		int min = prices[0];
		int ans = 0;
		for (int i = 0; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			ans = Math.max(ans, prices[i] - min);
		}
		return ans;
	}

}
