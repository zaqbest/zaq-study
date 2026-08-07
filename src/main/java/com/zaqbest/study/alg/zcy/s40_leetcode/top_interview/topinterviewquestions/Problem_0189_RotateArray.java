package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 原地转动数组
 * 要求：时间复杂度O(N）,空间复杂度O(1)
 *
 * 思路：
 * - 3次逆序（L逆序，R逆序，整体逆序）
 */
public class Problem_0189_RotateArray {

	public void rotate1(int[] nums, int k) {
		int N = nums.length;
		k = k % N;
		reverse(nums, 0, N - k - 1);
		reverse(nums, N - k, N - 1);
		reverse(nums, 0, N - 1);
	}

	public static void reverse(int[] nums, int L, int R) {
		while (L < R) {
			int tmp = nums[L];
			nums[L++] = nums[R];
			nums[R--] = tmp;
		}
	}

	public static void rotate2(int[] nums, int k) {
		int N = nums.length;
		k = k % N;
		if (k == 0) {
			return;
		}
		int L = 0;
		int R = N - 1;
		int lpart = N - k;
		int rpart = k;
		int same = Math.min(lpart, rpart);
		int diff = lpart - rpart;
		exchange(nums, L, R, same);
		while (diff != 0) {
			if (diff > 0) {
				L += same;
				lpart = diff;
			} else {
				R -= same;
				rpart = -diff;
			}
			same = Math.min(lpart, rpart);
			diff = lpart - rpart;
			exchange(nums, L, R, same);
		}
	}

	public static void exchange(int[] nums, int start, int end, int size) {
		int i = end - size + 1;
		int tmp = 0;
		while (size-- != 0) {
			tmp = nums[start];
			nums[start] = nums[i];
			nums[i] = tmp;
			start++;
			i++;
		}
	}

}
