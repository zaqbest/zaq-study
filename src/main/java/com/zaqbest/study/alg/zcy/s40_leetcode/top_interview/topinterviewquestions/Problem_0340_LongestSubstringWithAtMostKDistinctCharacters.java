package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 最多包含k个不同的字符，求最大的长度
 *
 * 思路
 * - 滑动窗口+记账本
 *
 * 往右扩不动，就收缩窗口
 */
public class Problem_0340_LongestSubstringWithAtMostKDistinctCharacters {

	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s == null || s.length() == 0 || k < 1) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] count = new int[256]; //记账本功能
		int diff = 0;
		int R = 0;
		int ans = 0;
		for (int i = 0; i < N; i++) {
			// R 窗口的右边界
			while (R < N && (diff < k || (diff == k && count[str[R]] > 0))) {
				diff += count[str[R]] == 0 ? 1 : 0;
				count[str[R++]]++;
			}
			// R 来到违规的第一个位置
			ans = Math.max(ans, R - i);
			diff -= count[str[i]] == 1 ? 1 : 0;
			count[str[i]]--;
		}
		return ans;
	}

}
