
package com.zaqbest.study.alg.zcy.s11_trainingcamp.term01.class04;

/**
 * KMP算法
 */
public class Code01_KMP {

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] str1 = s.toCharArray();
		char[] match = m.toCharArray();
		int x = 0;
		int y = 0;
		int[] next = getNextArray(match);
		while (x < str1.length && y < match.length) {
			//情况1：可以匹配上，则x++,y++
			if (str1[x] == match[y]) {
				x++;
				y++;
			//情况2.1，没有匹配上，并且y是第0个字符（也就解释了这里为什么没有y=0, 这就是next数组的规定）
			} else if (next[y] == -1) {
				x++;
			//情况2.2, 没有匹配上，m回退到适当的位置（使用next加速，利用已经匹配过的数据，x不用回退）
			} else {
				y = next[y];
			}
		}

		//退出while有两种情况，x用完了， y用完了， 只有y用完的情况才是匹配成功的
		return y == match.length ? x - y : -1;
	}

	private static int[] getNextArray(char[] ms) {
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
			if (ms[i - 1] == ms[cn]) {
				next[i++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
	}

	// for test
	public static String getRandomString(int possibilities, int size) {
		char[] ans = new char[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		String s = "ccabaxababcff";
		String m = "abaxababc";

		int res = getIndexOf(s, m);
		System.out.println(res);
	}
	public static void main0(String[] args) {
		int possibilities = 5;
		int strSize = 20;
		int matchSize = 5;
		int testTimes = 5000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strSize);
			String match = getRandomString(possibilities, matchSize);
			if (getIndexOf(str, match) != str.indexOf(match)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
