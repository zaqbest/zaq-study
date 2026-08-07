package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

import java.util.Stack;

/**
 * 逆波兰式（没啥意思的题目）
 *
 * 思路：
 * - 遇到数字压栈
 * - 遇到运算符，从栈里弹出两个数字处理
 */
public class Problem_0150_EvaluateReversePolishNotation {

	public static int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (String str : tokens) {
			if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
				compute(stack, str);
			} else {
				stack.push(Integer.valueOf(str));
			}
		}
		return stack.peek();
	}

	public static void compute(Stack<Integer> stack, String op) {
		int num2 = stack.pop();
		int num1 = stack.pop();
		int ans = 0;
		switch (op) {
		case "+":
			ans = num1 + num2;
			break;
		case "-":
			ans = num1 - num2;
			break;
		case "*":
			ans = num1 * num2;
			break;
		case "/":
			ans = num1 / num2;
			break;
		}
		stack.push(ans);
	}

}
