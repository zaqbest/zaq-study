package com.zaqbest.study.alg.zcy.s40_leetcode.top_100_like.toplikedquestions;

/**
 * 最长的有效括号
 *
 * 思路
 * - 动态规划
 * - 时间复杂度 O(N)
 */
public class Problem_0032_LongestValidParentheses {

	public static int longestValidParentheses(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] chas = str.toCharArray();
		int[] dp = new int[chas.length];
		int pre = 0;
		int res = 0;
		for (int i = 1; i < chas.length; i++) {
			if (chas[i] == ')') {
				// pre是，当前i位置的), 应该找哪个位置的左括号
				pre = i - dp[i - 1] - 1;
				if (pre >= 0 && chas[pre] == '(') {
					dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}


	public static int longestValidParentheses_study1(String s) {

		if (s == null || s.length() < 2){
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;

		int[] dp = new int[N];
		int ans = 0;
		for (int i = 1; i < N; i++){
			if (str[i] == ')'){
				int pre = i - dp[i-1] - 1;
				if (pre >= 0 && str[pre] == '('){
					dp[i] = dp[i-1] + 2;

					//接上pre之前的结果
					if (pre > 0){
						dp[i] += dp[pre-1];
					}

					ans = Math.max(ans, dp[i]);
				}
			}
		}

		return ans;
	}

}
