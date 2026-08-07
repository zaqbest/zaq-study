package com.zaqbest.study.alg.zcy.s30_great_offer.class15;

import com.zaqbest.study.alg.utils.ArrayUtil;

//leetcode 123
public class Code03_BestTimeToBuyAndSellStockIII {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int ans = 0;
		int doneOnceMinusBuyMax = -prices[0];
		int doneOnceMax = 0;
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
			doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
			doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
		}
		return ans;
	}

	/**
	 * dp算法
	 * ![](https://pic.zaqbest.com/i/2022/05/17/6283c33f7a02f.jpeg)
	 * @param prices
	 * @return
	 */
	public static int maxProfit_my1(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int N = prices.length;

		int dp[][] = new int[N][3];// 0,1,2
		for (int k = 1; k <=2; k++){
			int p1 = dp[0][k]; //不在1位置卖出；
			int best = 0 - prices[0];
			dp[1][k] = Math.max(p1, best + prices[1]);

			for (int i = 2; i < N; i++){
				p1 = dp[i-1][k]; //不在i位置卖出
				best = Math.max(best, dp[i-2][k-1]-prices[i-1]);
				dp[i][k] = Math.max(p1, best + prices[i]);
			}
		}

		return dp[N-1][2];
	}

	public static void main(String[] args) {
		//int[] prices = {6,1,3,2,4,7};
		System.out.println("begin!");
		for (int i = 0; i < 1000000; i++) {
			int[] prices = ArrayUtil.generateRandomArray(100, 100);
			int r1 = maxProfit(prices);
			int r2 = maxProfit_my1(prices);
			if (r1 != r2) {
				System.out.println(r1);
				System.out.println(r2);
				System.out.println("oops!");
				break;
			}
		}
		System.out.println("fininsh!");
	}

}
