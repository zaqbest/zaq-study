package com.zaqbest.study.alg.zcy.s30_great_offer.class09;

/**
 * 最长递增子序列（LIS），经典问题！！！！
 *
 * 本题测试链接 : https://leetcode.cn/problems/longest-increasing-subsequence
 *
 * 解法
 * - dp + 二分， 牛逼轰轰的解法
 */
public class Code03_LIS {

	public static int lengthOfLIS(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] ends = new int[arr.length];
		ends[0] = arr[0];
		int right = 0;//ends数组的有效区域为0..right
		int l = 0;
		int r = 0;
		int m = 0;
		int max = 1;
		for (int i = 1; i < arr.length; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = arr[i];
			max = Math.max(max, l + 1);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] arr = {10,9,2,5,3,7,101,18};
		int res = lengthOfLIS(arr);

		System.out.println(res);
	}
}