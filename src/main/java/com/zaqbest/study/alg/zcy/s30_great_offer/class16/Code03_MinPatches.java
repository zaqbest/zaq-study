package com.zaqbest.study.alg.zcy.s30_great_offer.class16;

import java.util.Arrays;

/**
 * 给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，
 * 使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示，请输出满足上述要求的最少需要补充的数字个数
 */
public class Code03_MinPatches {
	// arr请保证有序，且正数  1~aim
	public static int minPatches(int[] arr, int aim) {
		int patches = 0; // 缺多少个数字
		long range = 0; // 已经完成了1 ~ range的目标
		Arrays.sort(arr);
		for (int i = 0; i != arr.length; i++) {
			// arr[i]
			// 要求：1 ~ arr[i]-1 范围被搞定！
			while (arr[i] - 1 > range) { // arr[i] 1 ~ arr[i]-1
				//System.out.println(range+1);
				range += range + 1; // range + 1 是缺的数字
				patches++;
				if (range >= aim) {
					return patches;
				}
			}
			// 要求被满足了！
			range += arr[i];
			if (range >= aim) {
				return patches;
			}
		}
		//数组已经用完了，孤独的增长
		while (aim >= range + 1) {
			//System.out.println(range+1);
			range += range + 1;
			patches++;
		}
		return patches;
	}

	// 嘚瑟（range就是Integer, 可看可不看）
	public static int minPatches2(int[] arr, int K) {
		int patches = 0; // 缺多少个数字
		int range = 0; // 已经完成了1 ~ range的目标
		for (int i = 0; i != arr.length; i++) {
			// 1~range
			// 1 ~ arr[i]-1
			while (arr[i] > range + 1) { // arr[i] 1 ~ arr[i]-1

				if (range > Integer.MAX_VALUE - range - 1) {
					return patches + 1;
				}

				range += range + 1; // range + 1 是缺的数字
				patches++;
				if (range >= K) {
					return patches;
				}
			}
			if (range > Integer.MAX_VALUE - arr[i]) {
				return patches;
			}
			range += arr[i];
			if (range >= K) {
				return patches;
			}
		}
		while (K >= range + 1) {
			if (K == range && K == Integer.MAX_VALUE) {
				return patches;
			}
			if (range > Integer.MAX_VALUE - range - 1) {
				return patches + 1;
			}
			range += range + 1;
			patches++;
		}
		return patches;
	}

	public static void main(String[] args) {
		int[] test = { 1, 2, 31, 33 };
		int n = 500;
		System.out.println(minPatches(test, n));

	}
}
