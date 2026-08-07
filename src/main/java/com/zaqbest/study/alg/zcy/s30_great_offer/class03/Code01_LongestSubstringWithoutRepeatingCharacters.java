package com.zaqbest.study.alg.zcy.s30_great_offer.class03;

/**
 * 求一个字符串中，最长无重复字符子串长度
 *
 * 思路
 * - dp + map位置表
 *
 *本题测试链接 : https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class Code01_LongestSubstringWithoutRepeatingCharacters {

	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		map[str[0]] = 0;
		int N = str.length;
		int ans = 1; //当前字符获得的最大长度
		int pre = 1; //上一个字符获得的最大长度
		for (int i = 1; i < N; i++) {
			pre = Math.min(i - map[str[i]], pre + 1);
			ans = Math.max(ans, pre);
			map[str[i]] = i;
		}
		return ans;
	}

	public static void main(String[] args) {
		String s = "aabcdaef";
		int res = lengthOfLongestSubstring(s);
		System.out.println(res);
	}
}
