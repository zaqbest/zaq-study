package com.zaqbest.study.alg.zcy.s10_primary.class09;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大利润
 *
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 */
public class Code05_IPO {

	/**
	 *
	 * @param K 最多做K个项目
	 * @param W 初始资金
	 * @param Profits 利润数组
	 * @param Capital 成本数组
	 * @return 最大获取钱数
	 */
	public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
		//成本从小到大
		PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
		//利润从大到小
		PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		for (int i = 0; i < Profits.length; i++) {
			minCostQ.add(new Program(Profits[i], Capital[i]));
		}
		for (int i = 0; i < K; i++) {
			//把我当前能做的任务都取出来
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
				maxProfitQ.add(minCostQ.poll());
			}
			if (maxProfitQ.isEmpty()) {
				return W;
			}
			//获取一个最大利润
			W += maxProfitQ.poll().p;
		}
		return W;
	}

	public static class Program {
		public int p; //利润
		public int c; //成本

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	public static class MinCostComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.c - o2.c;
		}

	}

	public static class MaxProfitComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o2.p - o1.p;
		}

	}

}
