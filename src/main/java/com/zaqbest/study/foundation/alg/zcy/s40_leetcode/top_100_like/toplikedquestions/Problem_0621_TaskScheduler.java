package com.zaqbest.study.foundation.alg.zcy.s40_leetcode.top_100_like.toplikedquestions;

/**
 * 任务安排
 * 给定task数组，和冷却时间；相同的任务之间必须冷却free个时间
 *
 * 思路
 * - 贪心
 * - 思路清奇！！！
 */
public class Problem_0621_TaskScheduler {

	// ['A', 'B', 'A']
	public static int leastInterval(char[] tasks, int free) {
		int[] count = new int[256];
		// 出现最多次的任务，到底是出现了几次
		int maxCount = 0;
		for (char task : tasks) {
			count[task]++;
			maxCount = Math.max(maxCount, count[task]);
		}
		// 有多少种任务，都出现最多次
		int maxKinds = 0;
		for (int task = 0; task < 256; task++) {
			if (count[task] == maxCount) {
				maxKinds++;
			}
		}
		// 砍掉最后一组剩余的任务数
		int tasksExceptFinalTeam = tasks.length - maxKinds;
		int spaces = (free + 1) * (maxCount - 1); //总的位置数
		int restSpaces = Math.max(0, spaces - tasksExceptFinalTeam); //剩余空格数
		return tasks.length + restSpaces;
	}

}
