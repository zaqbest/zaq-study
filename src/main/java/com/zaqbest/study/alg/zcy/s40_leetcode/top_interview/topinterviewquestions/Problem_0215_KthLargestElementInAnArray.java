package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 第K大的数
 *
 * 思路
 * 随机快排改写
 * bfprt算法
 * 时间复杂度O(N)
 */
public class Problem_0215_KthLargestElementInAnArray {

	public int findKthLargest(int[] nums, int k) {
		return minKth(nums, nums.length + 1 - k);
	}

	public static int minKth(int[] arr, int k) {
		return process(arr, 0, arr.length - 1, k - 1);
	}

	public static int process(int[] arr, int L, int R, int index) {
		if (L == R) {
			return arr[L];
		}
		int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
		int[] range = partition(arr, L, R, pivot);
		if (index >= range[0] && index <= range[1]) {
			return arr[index];
		} else if (index < range[0]) {
			return process(arr, L, range[0] - 1, index);
		} else {
			return process(arr, range[1] + 1, R, index);
		}
	}

	public static int[] partition(int[] arr, int L, int R, int pivot) {
		int less = L - 1;
		int more = R + 1;
		int cur = L;
		while (cur < more) {
			if (arr[cur] < pivot) {
				swap(arr, ++less, cur++);
			} else if (arr[cur] > pivot) {
				swap(arr, cur, --more);
			} else {
				cur++;
			}
		}
		return new int[] { less + 1, more - 1 };
	}

	public static void swap(int[] arr, int i1, int i2) {
		int tmp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = tmp;
	}

}
