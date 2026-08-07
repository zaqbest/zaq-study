package com.zaqbest.study.alg.zcy.s40_leetcode.top_100_like.toplikedquestions;

import java.util.HashMap;

/**
 * 给定数组arr, 连续子数组的和为K, 求子数值最长多长
 *
 * 思路
 * - 前缀和
 * - 哈希表
 *
 * 升级问题
 * {@link Problem_0437_PathSumIII}
 */
public class Problem_0560_SubarraySumEqualsK {

	public static int subarraySum(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		HashMap<Integer, Integer> preSumTimesMap = new HashMap<>();
		preSumTimesMap.put(0, 1);
		int all = 0; // 0..i
		int ans = 0;
		for (int i = 0; i < nums.length; i++) {
			all += nums[i]; // 0....i 整体的前缀和
			if (preSumTimesMap.containsKey(all - k)) {
				ans += preSumTimesMap.get(all - k);
			}
			if (!preSumTimesMap.containsKey(all)) {
				preSumTimesMap.put(all, 1);
			} else {
				preSumTimesMap.put(all, preSumTimesMap.get(all) + 1);
			}
		}
		return ans;
	}

}
