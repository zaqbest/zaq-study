package com.zaqbest.study.alg.zcy.s11_trainingcamp.term03.class01;

/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 返回一个括号字符串中，最长的括号有效子串的长度
 */
public class Code03_ParenthesesDeep {

	/**
	 * 是否是有效的括号
	 *
	 * @param str
	 * @return
	 */
	public static boolean isValid(char[] str) {
		if (str == null || str.length == 0) {
			return false;
		}
		int status = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] != ')' && str[i] != '(') {
				return false;
			}
			if (str[i] == ')' && --status < 0) {
				return false;
			}
			if (str[i] == '(') {
				status++;
			}
		}
		return status == 0;
	}

	/**
	 * 括号的最大深度
	 *
	 * @param s
	 * @return
	 */
	public static int deep(String s) {
		char[] str = s.toCharArray();
		if (!isValid(str)) {
			return 0;
		}
		int count = 0;
		int max = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '(') {
				max = Math.max(max, ++count);
			} else {
				count--;
			}
		}
		return max;
	}

	/**
	 * 最长的括号有效子串的长度
	 *
	 * @param s
	 * @return
	 */
	public static int maxLength(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		int[] dp = new int[str.length];
		int pre = 0;
		int res = 0;
		for (int i = 1; i < str.length; i++) {
			if (str[i] == ')') {
				pre = i - dp[i - 1] - 1; // 与str[i]配对的左括号的位置 pre
				if (pre >= 0 && str[pre] == '(') {
					dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}

	public static void main(String[] args) {
		String test = "((()))";
		System.out.println(deep(test));

	}

}
