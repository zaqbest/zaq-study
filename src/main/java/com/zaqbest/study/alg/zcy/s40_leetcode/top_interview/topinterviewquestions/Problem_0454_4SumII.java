package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

import java.util.HashMap;

/**
 * 4个数组中，每个数组都出一个数，4个数的sum为0
 *
 * AB一组，CD一组
 * 时间复杂度O(N^2)
 */
public class Problem_0454_4SumII {

	public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				sum = A[i] + B[j];
				if (!map.containsKey(sum)) {
					map.put(sum, 1);
				} else {
					map.put(sum, map.get(sum) + 1);
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < D.length; j++) {
				sum = C[i] + D[j];
				if (map.containsKey(-sum)) {
					ans += map.get(-sum);
				}
			}
		}
		return ans;
	}

}
