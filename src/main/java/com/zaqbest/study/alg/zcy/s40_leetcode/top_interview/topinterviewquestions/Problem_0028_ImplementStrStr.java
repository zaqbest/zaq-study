package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * KMP算法
 */
public class Problem_0028_ImplementStrStr {

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || s.length() < m.length()) {
			return -1;
		}
		if (m.length() == 0) {
			return 0;
		}
		char[] str1 = s.toCharArray();
		char[] str2 = m.toCharArray();
		int x = 0;
		int y = 0;
		int[] next = getNextArray(str2);
		while (x < str1.length && y < str2.length) {
			if (str1[x] == str2[y]) {
				x++;
				y++;
			} else if (next[y] == -1) {
				x++;
			} else {
				y = next[y];
			}
		}
		//如果str2已经匹配完了，则最开始匹配的位置为x-y;
		//否则，说明x已经匹配完了，但是y还没有匹配完，=> 没有找到str2, 返回-1
		return y == str2.length ? x - y : -1;
	}

	/**
	 * 前k个字符恰等于后k个字符 的最大的k
	 * @param ms
	 * @return
	 */
	public static int[] getNextArray(char[] ms) {
		if (ms.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		// cn代表，cn位置的字符，是当前和i-1位置比较的字符
		int cn = 0;
		while (i < next.length) {
			if (ms[i - 1] == ms[cn]) { //如果匹配上，k的长度增加1
				next[i++] = ++cn;
			} else if (cn > 0) { //如果没有匹配上，退回到上一次待匹配的位置再次尝试
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
	}

	public static int strStr(String haystack, String needle) {
		return getIndexOf(haystack, needle);
	}

	public static void main(String[] args) {
		String str = "aabbccddccff";
		String needle = "abcdabcde";

		strStr(str, needle);
	}
}
