package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 子数组的最大累乘积
 *
 * 思路
 * 依次考虑以[i]作为结尾的情况
 * maxDp[i]表示[0..i-1]的最大值
 * minDp[i]表示[0..i-1]的最小值
 * 以[i]作为结尾的最大值，有下列3种情况
 * a）[i]单独使用
 * b）[i]和maxDp[i-1]相乘
 * c）[i]和minDp[i-1]相乘
 *
 * 求最小值的方法相同
 */
public class Problem_0152_MaximumProductSubarray {

	public static int maxProduct(int[] nums) {
		int ans = nums[0];
		int min = nums[0];
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int curmin = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
			int curmax = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
			min = curmin;
			max = curmax;
			ans  = Math.max(ans, max);
		}
		return ans;
	}

}
