package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * {@link Problem_0121_BestTimeToBuyAndSellStock}
 * {@link Problem_0122_BestTimeToBuyAndSellStockII}
 * {@link Problem_0123_BestTimeToBuyAndSellStockIII}
 * {@link Problem_0188_BestTimeToBuyAndSellStockIV}
 * {@link Problem_0309_BestTimeToBuyAndSellStockWithCooldown}
 */
public class Problem_0188_BestTimeToBuyAndSellStockIV {

	/**
	 * 带枚举行为的dp
	 * @param K
	 * @param arr
	 * @return
	 */
	public static int maxProfit1(int K, int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		if (K >= N / 2) {
			return allTrans(arr);
		}

		//dp[i][j]指的是从[0..i]最多操作j次，获得的最大收益
		int[][] dp = new int[N][K + 1];
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i - 1][j]; //[i]不参与
				for (int p = 0; p <= i; p++) {
					//[i]参与操作，并且是卖出；则买入行为可以是[0..i]
					dp[i][j] = Math.max(dp[p][j - 1] + arr[i] - arr[p], dp[i][j]);
				}
			}
		}
		return dp[N - 1][K];
	}

	/**
	 * 省掉枚举行为的版本（斜率优化）
	 * @param K
	 * @param prices
	 * @return
	 */
	public static int maxProfit2(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int N = prices.length;
		if (K >= N / 2) {
			return allTrans(prices);
		}
		int[][] dp = new int[K + 1][N];
		int ans = 0;
		for (int j = 1; j <= K; j++) {
			int pre = dp[j][0];
			int best = pre - prices[0];
			for (int i = 1; i < N; i++) {
				pre = dp[j - 1][i];
				dp[j][i] = Math.max(dp[j][i - 1], prices[i] + best);
				best = Math.max(best, pre - prices[i]);
				ans = Math.max(dp[j][i], ans);
			}
		}
		return ans;
	}

	/**
	 * 省掉枚举行为的版本（斜率优化）
	 * @param K
	 * @param prices
	 * @return
	 */
	public static int maxProfit3(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int N = prices.length;
		if (K >= N / 2) {
			return allTrans(prices);
		}
		int[][] dp = new int[K + 1][N];
		int ans = 0;
		for (int j = 1; j <= K; j++) {
			int best = dp[j][0] - prices[0];
			for (int i = 1; i < N; i++) {
				// 1,不考虑[i]的情况 2，枚举行为；两者PK取最大值
				dp[j][i] = Math.max(dp[j][i - 1], prices[i] + best);

				best = Math.max(best, dp[j - 1][i] - prices[i]);

				ans = Math.max(dp[j][i], ans);
			}
		}
		return ans;
	}

	public static int allTrans(int[] prices) {
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			ans += Math.max(prices[i] - prices[i - 1], 0);
		}
		return ans;
	}

}
