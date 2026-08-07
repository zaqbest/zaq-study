package com.zaqbest.study.alg.zcy.s11_trainingcamp.term03.class03;

/**
 * 最长公共子序列问题
 * @see <a href="https://leetcode-cn.com/problems/longest-common-subsequence/">1143. 最长公共子序列</a>
 *
 * {@link Code05_LCSubstring}
 */
public class Code04_LCSubsequence {

	public static String lcse(String str1, String str2) {
		if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
			return "";
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int[][] dp = getdp(chs1, chs2);
		int m = chs1.length - 1;
		int n = chs2.length - 1;
		char[] res = new char[dp[m][n]];
		int index = res.length - 1;
		//还原公共子序列
		while (index >= 0) {
			//str2[n]中的该字符不是公共子序列
			if (n > 0 && dp[m][n] == dp[m][n - 1]) {
				n--;
			//str1[m]中的该字符不是公共子序列
			} else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
				m--;
			//str1[m]和str2[n]是公共子序列
			} else {
				res[index--] = chs1[m];
				m--;
				n--;
			}
		}
		return String.valueOf(res);
	}

	/**
	 * 计算dp数组
	 * @param str1 作为行
	 * @param str2 作为列
	 * @return
	 */
	public static int[][] getdp(char[] str1, char[] str2) {
		int[][] dp = new int[str1.length][str2.length];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;//首字符
		//计算第一列
		for (int i = 1; i < str1.length; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
		}
		//计算第一行
		for (int j = 1; j < str2.length; j++) {
			dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
		}
		//计算整个dp数组
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				//如果当前字符相等，则+1
				if (str1[i] == str2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		String str1 = "A1BC2D3EFGH45I6JK7LMN";
		String str2 = "12OPQ3RST4U5V6W7XYZ";
		System.out.println(lcse(str1, str2));

	}
}