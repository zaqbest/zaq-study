package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 42 接雨水问题
 *
 */
public class Problem_0042_TrappingRainWater {

	public static int trap(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}
		int N = arr.length;
		int L = 1;
		int leftMax = arr[0];
		int R = N - 2;
		int rightMax = arr[N - 1];
		int water = 0;
		while (L <= R) {
			if (leftMax <= rightMax) {
				water += Math.max(0, leftMax - arr[L]); //如果左侧没有我高，我就接不到雨水了
				leftMax = Math.max(leftMax, arr[L++]);
			} else {
				water += Math.max(0, rightMax - arr[R]);
				rightMax = Math.max(rightMax, arr[R--]);
			}
		}
		return water;
	}

}
