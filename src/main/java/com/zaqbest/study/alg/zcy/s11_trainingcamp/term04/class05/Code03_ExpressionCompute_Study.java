package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class05;

import java.util.Deque;
import java.util.LinkedList;

public class Code03_ExpressionCompute_Study {

	public static int getValue(String s) {
		return value(s.toCharArray(), 0)[0];
	}

	private static int[] value(char[] str, int i){
		Deque<String> queue = new LinkedList<>();
		int cur = 0;
		int[] braValue = null;
		while (i < str.length && str[i] != ')'){
			if (str[i] >= '0' && str[i] <= '9'){
				cur  = cur * 10 + (str[i++] - '0');
			} else if (str[i] == '('){
				braValue = value(str, i + 1);
				cur = braValue[0];
				i = braValue[1] + 1;
			} else {
				//处理数字
				addNum(queue, cur);
				//加入运算符
				queue.addLast(String.valueOf(str[i++]));
				//当前值归零
				cur = 0;
			}
		}

		addNum(queue, cur);
		return new int[]{getNum(queue), i};
	}

	private static void addNum(Deque<String> queue, int num){
		if (!queue.isEmpty()){
			int cur = 0;
			String top = queue.pollLast();

			if (top.equals("+") || top.equals("-")){
				queue.addLast(top);
			} else {
				cur = Integer.valueOf(queue.pollLast());
				num = top.equals("*") ? cur * num : cur / num;
			}
		}

		queue.addLast(String.valueOf(num));
	}

	private static int getNum(Deque<String> queue){
		int res = 0;
		boolean add = true;
		String cur = null;
		int num = 0;
		while (!queue.isEmpty()){
			cur = queue.pollFirst();

			if (cur.equals("+")){
				add = true;
			} else if (cur.equals("-")){
				add =false;
			} else {
				num = Integer.valueOf(cur);
				res = add ? res + num : res - num;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		String exp = "48*((70-65)-43)+8*1";
		System.out.println(getValue(exp));
	}
}
