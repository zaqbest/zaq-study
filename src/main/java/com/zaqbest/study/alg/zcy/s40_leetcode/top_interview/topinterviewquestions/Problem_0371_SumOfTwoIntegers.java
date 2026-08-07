package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 获得两个数a,b相加的结果，但是不能用加法
 *
 * 思路
 * - 位运算
 * 异或->无进位相加
 * 与操作->进位信息
 */
public class Problem_0371_SumOfTwoIntegers {

	public static int getSum(int a, int b) {
		int sum = a;
		while (b != 0) {
			sum = a ^ b;
			b = (a & b) << 1;
			a = sum;
		}
		return sum;
	}

}
