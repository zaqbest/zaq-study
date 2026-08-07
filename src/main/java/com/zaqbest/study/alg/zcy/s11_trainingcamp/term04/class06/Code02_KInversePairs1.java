package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class06;

/**
 * 给定一个整数N，代表你有1～N这些数字。在给定一个整数K。你可以随意排列这些数字，但是每一种排列都有若干个逆序对。返回有多少种排列，正好有K个逆序对
 *
 * 例子1:
 * Input: n = 3, k = 0
 * Output: 1
 * 解释：
 * 只有[1,2,3]这一个排列有0个逆序对。
 *
 * 例子2:
 * Input: n = 3, k = 1
 * Output: 2
 * 解释
 * [3,2,1]有(3,2)、(3,1)、(2,1)三个逆序对，所以不达标。
 * 达标的只有：
 * [1,3,2]只有(3,2)这一个逆序对，所以达标。
 * [2,1,3]只有(2,1)这一个逆序对，所以达标。
 */
public class Code02_KInversePairs1 {

	public static int kInversePairs1(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		int mod = 1000000007;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int p = 1; p <= k; p++) {
				for (int r = Math.max(0, p - i + 1); r <= p; r++) {
					dp[i][p] += dp[i - 1][r];
					dp[i][p] %= mod;
				}
			}
		}
		return dp[n][k];
	}

	public static int kInversePairs2(int n, int k) {
		if (n < 1 || k < 0) {
			return 0;
		}
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;
		int mod = 1000000007;
		for (int i = 1; i <= n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= k; j++) {
				dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
				if (j >= i) {
					dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod;
				}
			}
		}
		return dp[n][k];
	}

}
