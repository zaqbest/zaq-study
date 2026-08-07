package com.zaqbest.study.alg.zcy.s10_primary.class11;

/**
 * 0-1背包问题
 *
 * 完全背包问题，限制次数背包问题可以修改得到
 */
public class Code07_Knapsack {

	/**
	 * 采用暴力递归算法，计算经典背包问题
	 *
	 * @param w 重量数组
	 * @param v 价值数组
	 * @param bag 背包容量
	 * @return
	 */
	public static int getMaxValue(int[] w, int[] v, int bag) {
		return process(w, v, 0, 0, bag);
	}

	// index... 最大价值
	public static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
		if (alreadyW > bag) {
			return -1;
		}
		// 重量没超（已经没有物品可以放了）
		if (index == w.length) {
			return 0;
		}
		//第index的物品没有放入的情况,index+1...的最大价值
		int p1 = process(w, v, index + 1, alreadyW, bag);
		//放入第index个物品的情况下，不超过背包容量的情况下，最大价值为v[index]+p2next，否则价值为-1
		int p2next = process(w, v, index + 1, alreadyW + w[index], bag);
		int p2 = -1;
		if (p2next != -1) {
			p2 = v[index] + p2next;
		}
		return Math.max(p1, p2);

	}

	public static int maxValue(int[] w, int[] v, int bag) {
		return process(w, v, 0, bag);
	}

	// 只剩下rest的空间了，
	// index...货物自由选择，但是不要超过rest的空间
	// 返回能够获得的最大价值
	public static int process(int[] w, int[] v, int index, int rest) {
		if (rest <= 0) { // base case 1
			return 0;
		}
		// rest >=0
		if (index == w.length) { // base case 2
			return 0;
		}
		// 有货也有空间
		int p1 = process(w, v, index + 1, rest);
		int p2 = Integer.MIN_VALUE;
		if (rest >= w[index]) {
			p2 = v[index] + process(w, v, index + 1, rest - w[index]);
		}
		return Math.max(p1, p2);
	}

	public static int dpWay(int[] w, int[] v, int bag) {
		int N = w.length;
		int[][] dp = new int[N + 1][bag + 1];
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 1; rest <= bag; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (rest >= w[index]) {
					dp[index][rest] = Math.max(dp[index][rest], v[index] + dp[index + 1][rest - w[index]]);
				}
			}
		}
		return dp[0][bag];
	}

	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7 };
		int[] values = { 5, 6, 3, 19 };
		int bag = 11;
		System.out.println(maxValue(weights, values, bag));
		System.out.println(dpWay(weights, values, bag));
	}

}
