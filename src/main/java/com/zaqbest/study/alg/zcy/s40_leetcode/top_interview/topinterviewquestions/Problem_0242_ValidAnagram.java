package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 是否同构词
 *
 * 思路
 * 统计词频，遍历str1每种字符++，遍历str2每种字符--，
 * 只要发现小于0，就是非法字符
 */
public class Problem_0242_ValidAnagram {

	public static boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] tar = t.toCharArray();
		int[] count = new int[256];
		for (char cha : str) {
			count[cha]++;
		}
		for (char cha : tar) {
			if (--count[cha] < 0) {
				return false;
			}
		}
		return true;
	}

}
