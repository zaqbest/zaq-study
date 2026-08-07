package com.zaqbest.study.alg.zcy.s30_great_offer.class03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 1755. 最接近目标值的子序列和
 *
 * 本题测试链接 : https://leetcode.cn/problems/closest-subsequence-sum/
 * 本题数据量描述:
 * 1 <= nums.length <= 40
 * -10^7 <= nums[i] <= 10^7
 * -10^9 <= goal <= 10^9
 * 通过这个数据量描述可知，需要用到分治，因为数组长度不大
 * 而值很大，用动态规划的话，表会爆
 *
 * 思路
 * - 分治！！！根据数据量猜解法
 *
 */
public class Code06_ClosestSubsequenceSum {

	public static int[] l = new int[1 << 20]; //即使20个数字所有的组合结果都不同，最大组合数2^20
	public static int[] r = new int[1 << 20];

	public static int minAbsDifference(int[] nums, int goal) {
		if (nums == null || nums.length == 0) {
			return goal;
		}
		int le = process(nums, 0, nums.length >> 1, 0, 0, l);
		int re = process(nums, nums.length >> 1, nums.length, 0, 0, r);
		Arrays.sort(l, 0, le);
		Arrays.sort(r, 0, re);
		//最差情况为abs(goal)
		int ans = Math.abs(goal);
		// re作为下标使用，需要减去1
		re--;
		for (int i = 0; i < le; i++) {
			int rest = goal - l[i];
			while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
				re--; //舍弃一些不可能是答案的尝试过程，或者叫剪枝
			}
			ans = Math.min(ans, Math.abs(rest - r[re]));
		}
		return ans;
	}

	public static int process(int[] nums, int index, int end, int sum, int fill, int[] arr) {
		if (index == end) {
			arr[fill++] = sum;
		} else {
			fill = process(nums, index + 1, end, sum, fill, arr);
			fill = process(nums, index + 1, end, sum + nums[index], fill, arr);
		}
		return fill;
	}

	public static int minAbsDifference_study1(int[] nums, int goal) {
		int[] ans = new int[1];
		ans[0] = Integer.MAX_VALUE;
		LinkedList<Integer> path = new LinkedList<>();
		process_study1(nums, goal, 0, path, ans);
		return ans[0];
	}

	public static void process_study1(int[] nums, int goal, int index, LinkedList<Integer> path, int[] ans){
		if (index == nums.length){
			int sum = 0;
			for (Integer n: path){
				sum += n;
			}
			ans[0] = Math.min(ans[0],Math.abs(sum-goal));
		} else {
			process_study1(nums, goal, index+1, path, ans);

			path.offerLast(nums[index]);
			process_study1(nums, goal, index+1, path, ans);
			path.pollLast();
		}
	}


	public static int minAbsDifference_study2(int[] nums, int goal) {
		int ans = process_study2(nums, goal, 0);
		return ans;
	}

	public static int  process_study2(int[] nums, int goal, int index){
		if (index == nums.length){
			return Math.abs(goal);
		} else {
			int ans = Integer.MAX_VALUE;
			int p1 = process_study2(nums, goal, index+1);
			int p2 = process_study2(nums, goal-nums[index], index+1);
			return Math.min(ans, Math.min(p1,p2));
		}
	}

	public static int minAbsDifference_study3(int[] nums, int goal) {
		Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
		int ans = process_study3(nums, goal, 0,  dp);
		return ans;
	}

	public static int  process_study3(int[] nums, int goal, int index,Map<Integer, Map<Integer, Integer>> dp){

		if (dp.containsKey(index) && dp.get(index).containsKey(goal)){
			return dp.get(index).get(goal);
		}

		if (index == nums.length){
			Map<Integer, Integer> value = new HashMap<>();
			value.put(goal,Math.abs(goal));
			dp.put(index, value);

			return Math.abs(goal);
		} else {
			int ans = Integer.MAX_VALUE;
			int p1 = process_study3(nums, goal, index+1, dp);
			int p2 = process_study3(nums, goal-nums[index], index+1, dp);
			ans =  Math.min(ans, Math.min(p1,p2));

			Map<Integer, Integer> value = new HashMap<>();
			value.put(goal, ans);
			dp.put(index, value);

			return ans;
		}
	}

	public static void main(String[] args) {
		int[] nums = {5,-7,3,5};
		int  goal = 6;

		System.out.println(minAbsDifference_study1(nums, goal));
		System.out.println(minAbsDifference_study2(nums, goal));
	}
}
