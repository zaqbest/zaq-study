package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 给定整数n, 求n!最后有多少个0
 *
 * 思路：
 * 因子中2的个数肯定比5多，所以每一个5因子肯定有2的因子和它匹配
 *
 * ans = n /5 + n/5^2 + n/5^3 + ....
 *
 * 有意思的题目
 */
public class Problem_0172_FactorialTrailingZeroes {

	public static int trailingZeroes(int n) {
		int ans = 0;
		while (n != 0) {
			n /= 5;
			ans += n;
		}
		return ans;
	}

}
