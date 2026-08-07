package com.zaqbest.study.alg.zcy.s10_primary.class02;

/**
 * 求数组arr[L..R]中的最大值，怎么用递归方法实现。
 *
 * 1）将[L..R]范围分成左右两半。左：[L..Mid]  右[Mid+1..R]
 * 2）左部分求最大值，右部分求最大值
 * 3） [L..R]范围上的最大值，是max{左部分最大值，右部分最大值}
 *
 * 注意：2）是个递归过程，当范围上只有一个数，就可以不用再递归了
 */
public class Code08_GetMax {

	// 求arr中的最大值
	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}

	// arr[L..R]范围上求最大值
	public static int process(int[] arr, int L, int R) {
		if (L == R) { // arr[L..R]范围上只有一个数，直接返回，base case
			return arr[L];
		}
		//  L..mid  mid+1...R
		// int mid = (L+R)/2
		int mid = L + ((R - L) >> 1); // 中点
		int leftMax = process(arr, L, mid);
		int rightMax = process(arr, mid + 1, R);
		return Math.max(leftMax, rightMax);
	}

}
