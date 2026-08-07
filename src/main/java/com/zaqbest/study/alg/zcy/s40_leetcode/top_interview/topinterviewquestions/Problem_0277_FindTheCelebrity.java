package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 如何在人群中找到明星
 *
 * 思路
 * - 想法很奇妙
 */
public class Problem_0277_FindTheCelebrity {

	// 提交时不要提交这个函数，只提交下面的方法
	public static boolean knows(int x, int i) {
		return true;
	}

	public int findCelebrity(int n) {
		int cand = 0;
		for (int i = 0; i < n; ++i) {
			if (knows(cand, i)) {
				cand = i;
			}
		}
		for (int i = 0; i < cand; ++i) {
			if (knows(cand, i)) {
				return -1;
			}
		}
		for (int i = 0; i < n; ++i) {
			if (!knows(i, cand)) {
				return -1;
			}
		}
		return cand;
	}

}
