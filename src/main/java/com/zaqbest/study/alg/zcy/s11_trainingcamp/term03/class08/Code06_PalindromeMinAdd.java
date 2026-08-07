package com.zaqbest.study.alg.zcy.s11_trainingcamp.term03.class08;

/**
 * 给定一个字符串，如果可以在任意位置添加字符，最少添加几个能让字符串整体都是回文串。
 */
public class Code06_PalindromeMinAdd {

	public static String getPalindrome1(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		char[] str = s.toCharArray();
		int[][] dp = getDP(str);
		//根据DP还原出字符串
		char[] res = new char[str.length + dp[0][str.length - 1]];
		int i = 0;
		int j = str.length - 1;
		int resl = 0;
		int resr = res.length - 1;
		while (i <= j) {
			if (str[i] == str[j]) {
				res[resl++] = str[i++];
				res[resr--] = str[j--];
			} else if (dp[i][j - 1] < dp[i + 1][j]) {
				res[resl++] = str[j];
				res[resr--] = str[j--];
			} else {
				res[resl++] = str[i];
				res[resr--] = str[i++];
			}
		}
		return String.valueOf(res);
	}

	public static int[][] getDP(char[] str) {
		//dp[i][j]指的是str[i..j]如果要变成回文，至少需要添加多少个字符
		int[][] dp = new int[str.length][str.length];
		for (int j = 1; j < str.length; j++) {
			dp[j - 1][j] = str[j - 1] == str[j] ? 0 : 1;
			for (int i = j - 2; i > -1; i--) {
				if (str[i] == str[j]) {
					dp[i][j] = dp[i + 1][j - 1];
				} else {
					dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
				}
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		String str = "AB1CD2EFG3H43IJK2L1MN";
		System.out.println(getPalindrome1(str));
	}

}
