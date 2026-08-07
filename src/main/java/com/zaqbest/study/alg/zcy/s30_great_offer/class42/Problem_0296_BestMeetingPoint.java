package com.zaqbest.study.alg.zcy.s30_great_offer.class42;

/**
 * 付费题：最佳开会地点问题
 *
 * 描述：给定一个M*N的矩阵，grid[i][j]的值1为有人，0位无人。
 * 现在要把所有的人都聚集到一个格子里开会，
 * 求所有人的移动距离累计值最小是多少？
 *
 * 思路：
 * 	- 先决定在哪一行，再决定在那一列，得到最终结果
 */
public class Problem_0296_BestMeetingPoint {

	public static int minTotalDistance(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		int[] iOnes = new int[N]; //统一每一行有多少人
		int[] jOnes = new int[M]; //统计每一列有多少人
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 1) {
					iOnes[i]++;
					jOnes[j]++;
				}
			}
		}
		// 开始以行为单位移动
		int total = 0;
		int i = 0;
		int j = N - 1;
		int iRest = 0;
		int jRest = 0;
		while (i < j) {
			// 向下移动
			if (iOnes[i] + iRest <= iOnes[j] + jRest) {
				total += iOnes[i] + iRest;
				iRest += iOnes[i++];
			}
			// 向上移动
			else {
				total += iOnes[j] + jRest;
				jRest += iOnes[j--];
			}
		}

		// 开始以列为单位移动
		i = 0;
		j = M - 1;
		iRest = 0;
		jRest = 0;
		while (i < j) {
			//向右移动
			if (jOnes[i] + iRest <= jOnes[j] + jRest) {
				total += jOnes[i] + iRest;
				iRest += jOnes[i++];
			}
			//像左移动
			else {
				total += jOnes[j] + jRest;
				jRest += jOnes[j--];
			}
		}
		return total;
	}

}
