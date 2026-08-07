package com.zaqbest.study.alg.zcy.s11_trainingcamp.term03.class06;

/**
 * 给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有字符， 而且在aim中属于str1的字符之间保持原来在str1中的顺序，属于str2的字符之间保持 原来在str2中的顺序，那么称aim是str1和str2的交错组成。实现一个函数，判断aim是 否是str1和str2交错组成
 * 【举例】 str1="AB"，str2="12"。那么"AB12"、"A1B2"、"A12B"、"1A2B"和"1AB2"等都是 str1 和 str2 的 交错组成
 *
 * 思路：
 * 1) 如果没有重复字符，可以使用merge合并
 * 2) 如果存在重复字符，dp问题
 */
public class Code06_StringCross {

	public static boolean isCross1(String s1, String s2, String ai) {
		if (s1 == null || s2 == null || ai == null) {
			return false;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		char[] aim = ai.toCharArray();
		if (aim.length != str1.length + str2.length) {
			return false;
		}
		//dp[i][j]指的是ai[0..i+j]是否可以由str1[0..i]和str2[0..j]组成
		boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
		dp[0][0] = true;
		for (int i = 1; i <= str1.length; i++) {
			if (str1[i - 1] != aim[i - 1]) {
				break;
			}
			dp[i][0] = true;
		}
		for (int j = 1; j <= str2.length; j++) {
			if (str2[j - 1] != aim[j - 1]) {
				break;
			}
			dp[0][j] = true;
		}
		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {

				//如果aim[i+j-1]可以由(str1[0..i-1]和str2[0..j])或者(str1[0..i]和str2[0..j-1])构成
				//那么再增加一个字符，则aim[i+j]只可能是str1[i]或者str2[j]位置的字符
				if (
						
						(str1[i - 1] == aim[i + j - 1] && dp[i - 1][j])
						
						|| 
						
						(str2[j - 1] == aim[i + j - 1] && dp[i][j - 1])
						
						
				) {
					
					
					dp[i][j] = true;
					
					
				}
				
				
				
			}
		}
		return dp[str1.length][str2.length];
	}

	public static boolean isCross2(String str1, String str2, String aim) {
		if (str1 == null || str2 == null || aim == null) {
			return false;
		}
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		char[] chaim = aim.toCharArray();
		if (chaim.length != ch1.length + ch2.length) {
			return false;
		}
		char[] longs = ch1.length >= ch2.length ? ch1 : ch2;
		char[] shorts = ch1.length < ch2.length ? ch1 : ch2;
		boolean[] dp = new boolean[shorts.length + 1];
		dp[0] = true;
		for (int i = 1; i <= shorts.length; i++) {
			if (shorts[i - 1] != chaim[i - 1]) {
				break;
			}
			dp[i] = true;
		}
		for (int i = 1; i <= longs.length; i++) {
			dp[0] = dp[0] && longs[i - 1] == chaim[i - 1];
			for (int j = 1; j <= shorts.length; j++) {
				if ((longs[i - 1] == chaim[i + j - 1] && dp[j]) || (shorts[j - 1] == chaim[i + j - 1] && dp[j - 1])) {
					dp[j] = true;
				} else {
					dp[j] = false;
				}
			}
		}
		return dp[shorts.length];
	}

	public static void main(String[] args) {
		String str1 = "1234";
		String str2 = "abcd";
		String aim = "1a23bcd4";
		System.out.println(isCross1(str1, str2, aim));
		System.out.println(isCross2(str1, str2, aim));

	}

}
