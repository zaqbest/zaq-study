package com.zaqbest.study.alg.zcy.s10_primary.class11;

import java.util.Stack;

public class Code04_ReverseStackUsingRecursive {

	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = f(stack);
		reverse(stack);
		stack.push(i);
	}

	/**
	 * 返回栈底元素
	 *
	 * @param stack
	 * @return
	 */
	public static int f(Stack<Integer> stack) {
		int result = stack.pop();//每次使用系统栈保存栈顶元素
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = f(stack);
			stack.push(result);
			return last;
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
