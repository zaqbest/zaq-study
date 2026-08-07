package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 最长递增子序列
 *
 * 实际复杂度O(N*logN)
 */
public class Problem_0300_LongestIncreasingSubsequence {

	public static int lengthOfLIS(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		//ens[i]指的是长度为i+1的递增子序列的最小值
		int[] ends = new int[arr.length];
		ends[0] = arr[0];
		int right = 0; //ends数组目前扩展到的最右端
		int l = 0;
		int r = 0;
		int m = 0;
		int max = 1;
		for (int i = 1; i < arr.length; i++) {
			l = 0;
			r = right;
			//找到arr[i]接在哪个ends[x]后面，使得递增子序列最长
			while (l <= r) {
				m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			//如果
			right = Math.max(right, l);
			ends[l] = arr[i];
			max = Math.max(max, l + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] arr = new int[]{3,1,6,2,4,3,4,0};
		lengthOfLIS(arr);
	}

}
