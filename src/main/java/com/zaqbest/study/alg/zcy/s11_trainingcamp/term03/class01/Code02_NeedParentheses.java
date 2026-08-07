package com.zaqbest.study.alg.zcy.s11_trainingcamp.term03.class01;

/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 有效的：    (())  ()()   (()())  等
 * 无效的：     (()   )(     等
 * 问题一：怎么判断一个括号字符串有效？(栈，使用count变量计数即可）
 * 问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
 */
public class Code02_NeedParentheses {

	/**
	 * 问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
	 *
	 * @param str
	 * @return
	 */
	public static int needParentheses(String str) {
		int t = 0;
		int needSolveRight = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				t++;
			} else { // 遇到的是')'
				if (t == 0) {
					needSolveRight++;
				} else {
					t--;
				}
			}
		}
		return t + needSolveRight;
	}

	public static void main(String args[]) {

	}

}
