package com.zaqbest.study.alg.zcy.s11_trainingcamp.term05.class01;

import com.zaqbest.study.alg.leetcode.Problem_0076_MinWindow;

/**
 * 给定字符串str1和str2，求str1的子串中含有str2所有字符的最小子串长度
 * 【举例】
 * str1="abcde"，str2="ac"
 * 因为"abc"包含 str2 所有的字符，并且在满足这一条件的str1的所有子串中，"abc"是 最短的，返回3。
 * str1="12345"，str2="344" 最小包含子串不存在，返回0。
 *
 * {@link Problem_0076_MinWindow}
 */
public class Problem02_MinWindowLength {

	public static int minLength(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() < s2.length()) {
			return Integer.MAX_VALUE;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[] map = new int[256]; // map[37] = 4  37  4次
		for (int i = 0; i != str2.length; i++) {
			map[str2[i]]++;
		}
		int left = 0;
		int right = 0;
		int all = str2.length;
		int minLen = Integer.MAX_VALUE;
		// [left, right)  [left, right-1]    [0,0)
		// R右扩   L ==0  R
		while (right != str1.length) {
			map[str1[right]]--;
			if (map[str1[right]] >= 0) {
				all--;
			}
			if (all == 0) { // 还完了
				while (map[str1[left]] < 0) {
					map[str1[left++]]++;
				}
				// [L..R]
				minLen = Math.min(minLen, right - left + 1);
				all++;
				map[str1[left++]]++;
			}
			right++;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	public static void main(String[] args) {
		String str1 = "adabbca";
		String str2 = "acb";
		System.out.println(minLength(str1, str2));

	}

}
