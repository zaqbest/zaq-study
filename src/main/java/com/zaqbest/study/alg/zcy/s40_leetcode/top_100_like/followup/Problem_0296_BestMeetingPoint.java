package com.zaqbest.study.alg.zcy.s40_leetcode.top_100_like.followup;

/**
 * 开会问题
 * 问题描述
 * https://pic.zaqbest.com/i/2022/04/30/626cbc0004202.png
 *
 * 这是一个性感的问题
 * 思路
 * - 本质是一个贪心
 * - 先考虑整行的移动，再考虑整列的移动
 */
public class Problem_0296_BestMeetingPoint {

	public static int minTotalDistance(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		int[] iOnes = new int[N];
		int[] jOnes = new int[M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 1) {
					iOnes[i]++;
					jOnes[j]++;
				}
			}
		}
		int total = 0;
		int i = 0;
		int j = N - 1;
		int iRest = 0;
		int jRest = 0;
		while (i < j) {
			if (iOnes[i] + iRest <= iOnes[j] + jRest) {
				total += iOnes[i] + iRest;
				iRest += iOnes[i++];
			} else {
				total += iOnes[j] + jRest;
				jRest += iOnes[j--];
			}
		}
		i = 0;
		j = M - 1;
		iRest = 0;
		jRest = 0;
		while (i < j) {
			if (jOnes[i] + iRest <= jOnes[j] + jRest) {
				total += jOnes[i] + iRest;
				iRest += jOnes[i++];
			} else {
				total += jOnes[j] + jRest;
				jRest += jOnes[j--];
			}
		}
		return total;
	}

}
