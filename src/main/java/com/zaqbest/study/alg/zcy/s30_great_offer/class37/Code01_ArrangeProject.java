package com.zaqbest.study.alg.zcy.s30_great_offer.class37;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  来自网易
 *  刚入职网易互娱，新人mini项目便如火如荼的开展起来。为了更好的项目协作与管理，
 *  小易决定将学到的甘特图知识用于mini项目时间预估。小易先把项目中每一项工作以任务的形式列举出来，
 *  每项任务有一个预计花费时间与前置任务表，必须完成了该任务的前置任务才能着手去做该任务。
 *  作为经验PM，小易把任务划分得井井有条，保证没有前置任务或者前置任务全数完成的任务，都可以同时进行。
 *  小易给出了这样一个任务表，请作为程序的你计算需要至少多长时间才能完成所有任务。
 *  输入第一行为一个正整数T，表示数据组数。
 *  对于接下来每组数据，第一行为一个正整数N，表示一共有N项任务。
 *  接下来N行，每行先有两个整数Di和Ki，表示完成第i个任务的预计花费时间为Di天，该任务有Ki个前置任务。
 *  之后为Ki个整数Mj，表示第Mj个任务是第i个任务的前置任务。
 *  数据范围：对于所有数据，满足1<=T<=3, 1<=N, Mj<=100000, 0<=Di<=1000, 0<=sum(Ki)<=N*2。
 *
 *  思路：
 *  - 拓扑排序 + 队列
 */
public class Code01_ArrangeProject {

	/**
	 * @param nums num[i]指的是第i个任务的后置任务列表
	 * @param days 每个任务的天数
	 * @param headCount headCount[i]值得是第i个任务的前置任务数
	 * @return
	 */
	public static int dayCount(ArrayList<Integer>[] nums, int[] days, int[] headCount) {
		Queue<Integer> head = countHead(headCount);
		int maxDay = 0;
		int[] countDay = new int[days.length];
		while (!head.isEmpty()) {
			int cur = head.poll();
			countDay[cur] += days[cur];
			for (int j = 0; j < nums[cur].size(); j++) {
				headCount[nums[cur].get(j)]--; //后续节点，入度减1
				if (headCount[nums[cur].get(j)] == 0) { //入度为0，放入队列
					head.offer(nums[cur].get(j));
				}
				//nums[cur].get(j) 最少要多少时间可以完成
				countDay[nums[cur].get(j)] = Math.max(countDay[nums[cur].get(j)], countDay[cur]);
			}
		}
		for (int i = 0; i < countDay.length; i++) {
			maxDay = Math.max(maxDay, countDay[i]);
		}
		return maxDay;
	}

	private static Queue<Integer> countHead(int[] headCount) {
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < headCount.length; i++) {
			if (headCount[i] == 0)
				queue.offer(i); // 没有前驱任务
		}
		return queue;
	}

	public static void main(String[] args) {
		ArrayList<Integer>[] nums = new ArrayList[4];
		nums[0] = new ArrayList<>(Arrays.asList(1,2,3));
		nums[1] = new ArrayList<>(Arrays.asList(2,3));
		nums[2] = new ArrayList<>();
		nums[3] = new ArrayList<>();

		int[] days = {7,3,2,5};
		int[] headCount = {0,1,2,2};

		int res = dayCount(nums, days, headCount);
		System.out.println(res);
	}
}
