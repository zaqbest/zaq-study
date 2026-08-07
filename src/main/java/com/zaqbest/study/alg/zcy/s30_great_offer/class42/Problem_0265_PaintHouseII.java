package com.zaqbest.study.alg.zcy.s30_great_offer.class42;

/**
 * 刷房子问题
 *
 * 思路：
 * - 最优解：贪心策略，第index房子刷什么颜色由index-1号房子决定
 */
public class Problem_0265_PaintHouseII {

	// costs[i][k] i号房子用k颜色刷的花费
	// 要让0...N-1的房子相邻不同色
	// 返回最小花费
	public static int minCostII(int[][] costs) {
		int N = costs.length;
		if (N == 0) {
			return 0;
		}
		int K = costs[0].length;
		int allMin1 = 0; //前一个房子最优的成本
		int allEnd1 = -1; //前一个房子最优的时的颜色
		int allMin2 = 0; //前一个房子次优的成本
		int allEnd2 = -1; //前一个房子次优时的颜色
		for (int i = 0; i < N; i++) {
			int curMin1 = Integer.MAX_VALUE;
			int curEnd1 = -1;
			int curMin2 = Integer.MAX_VALUE;
			int curEnd2 = -1;
			for (int j = 0; j < K; j++) {
				//如果不是前一个房子的颜色一样，此时就可以使用allMin1
				if (j != allEnd1) {
					// PK赢了最优成本，把当前的最优给次优（curMin2=curMin1), 然后刷新curMin1
					if (allMin1 + costs[i][j] < curMin1) {
						curMin2 = curMin1;
						curEnd2 = curEnd1;
						curMin1 = allMin1 + costs[i][j];
						curEnd1 = j;
					}
					// 没有PK赢curMin1,但是PK赢了curMin2, 只需要刷新curMin2即可
					else if (allMin1 + costs[i][j] < curMin2) {
						curMin2 = allMin1 + costs[i][j];
						curEnd2 = j;
					}
				}
				// 和前一个房子的颜色一样了，只能退而求其次使用allMin2进行PK
				else if (j != allEnd2) {
					if (allMin2 + costs[i][j] < curMin1) {
						curMin2 = curMin1;
						curEnd2 = curEnd1;
						curMin1 = allMin2 + costs[i][j];
						curEnd1 = j;
					} else if (allMin2 + costs[i][j] < curMin2) {
						curMin2 = allMin2 + costs[i][j];
						curEnd2 = j;
					}
				}
			}
			allMin1 = curMin1;
			allEnd1 = curEnd1;
			allMin2 = curMin2;
			allEnd2 = curEnd2;
		}
		return allMin1;
	}

}
