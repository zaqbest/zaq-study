package com.zaqbest.study.alg.zcy.s30_great_offer.class04;

/**
 * 交错组成字符串
 * 本题测试链接 : https://leetcode.cn/problems/interleaving-string/
 *
 * 思路
 * - 多样本对应尝试模型
 */
public class Code07_InterleavingString {

	public static boolean isInterleave(String s1, String s2, String s3) {
		if (s1 == null || s2 == null || s3 == null) {
			return false;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		char[] str3 = s3.toCharArray();
		if (str3.length != str1.length + str2.length) {
			return false;
		}
		//dp[i][j]值得是str3[0..i+j-1]能否由str1[0..i-1]和str2[0..j-1]组成
		//dp的默认值是false
		boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
		dp[0][0] = true;
		//填写第一列
		for (int i = 1; i <= str1.length; i++) {
			if (str1[i - 1] != str3[i - 1]) {
				break;
			}
			dp[i][0] = true;
		}
		//填写第一行
		for (int j = 1; j <= str2.length; j++) {
			if (str2[j - 1] != str3[j - 1]) {
				break;
			}
			dp[0][j] = true;
		}
		//普遍位置
		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				if (
						//最后一个字符来自str1, 并且dp[i-1][j]为true
						(str1[i - 1] == str3[i + j - 1] && dp[i - 1][j])
						||



						(str2[j - 1] == str3[i + j - 1] && dp[i][j - 1])


						) {
					dp[i][j] = true;
				}
			}
		}
		return dp[str1.length][str2.length];
	}

}
