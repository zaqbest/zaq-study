package com.zaqbest.study.alg.zcy.s30_great_offer.class17;

import java.util.HashMap;

/**
 * 不同的子序列 II
 *
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
 *
 * 本题测试链接 : https://leetcode.cn/problems/distinct-subsequences-ii/
 *
 * 思路：
 * - 在获取i-1结果的情况下，考虑再加入1个字符，会有多少中情况。
 * - 空集合加入一个字符，可能会有现有字符重复，需要减去
 *
 * 实现看Code05_DistinctSubseqValue#zuo(java.lang.String)就可以了
 *
 */
public class Code05_DistinctSubseqValue {

	public static int distinctSubseqII(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int m = 1000000007;
		char[] str = s.toCharArray();
		int[] count = new int[26];
		int all = 1; // 算空集
		for (char x : str) {
			int add = (all - count[x - 'a'] + m) % m;
			all = (all + add) % m;
			count[x - 'a'] = (count[x - 'a'] + add) % m;
		}
		return all - 1;
	}

	/**
	 * 空集是算一个序列， 这是与原题目的不同点
	 *
	 * @param s
	 * @return
	 */
	public static int zuo(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int m = 1000000007;
		char[] str = s.toCharArray();
		HashMap<Character, Integer> map = new HashMap<>();
		int all = 1; // 一个字符也没遍历的时候，有空集
		for (char x : str) {
			int newAdd = all;
//			int curAll = all + newAdd - (map.containsKey(x) ? map.get(x) : 0);
			int curAll = all;
			curAll = (curAll + newAdd) % m;
			curAll = (curAll - (map.containsKey(x) ? map.get(x) : 0) + m) % m;
			all = curAll;
			map.put(x, newAdd);
		}
		return all;
	}

	public static void main(String[] args) {
		String s = "aba";
		System.out.println(distinctSubseqII(s) + 1);
		System.out.println(zuo(s));
	}

}
