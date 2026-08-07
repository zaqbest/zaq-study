package com.zaqbest.study.alg.zcy.s30_great_offer.class07;

import com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class03.Code01_MaxGap;

/**
 * 最大间隙问题
 *
 * 测试链接 : https://leetcode.cn/problems/maximum-gap/
 *
 * 编码技巧：  min, max hasNum数组
 * 桶排序思想，抽屉思想
 *
 * {@link Code01_MaxGap#maxGap(int[])}
 */
public class Code03_MaxGap {

	public static int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int len = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if (min == max) {
			return 0;
		}
		boolean[] hasNum = new boolean[len + 1];
		int[] maxs = new int[len + 1];
		int[] mins = new int[len + 1];
		int bid = 0;
		for (int i = 0; i < len; i++) {
			bid = bucket(nums[i], len, min, max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		int res = 0;
		int lastMax = maxs[0];
		int i = 1;
		for (; i <= len; i++) {
			if (hasNum[i]) {
				res = Math.max(res, mins[i] - lastMax);
				lastMax = maxs[i];
			}
		}
		return res;
	}

	public static int bucket(long num, long len, long min, long max) {
		return (int) ((num - min) * len / (max - min));
	}

}
