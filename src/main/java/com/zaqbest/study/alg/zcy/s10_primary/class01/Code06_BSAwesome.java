package com.zaqbest.study.alg.zcy.s10_primary.class01;

/**
 * 局部最小值
 */
public class Code06_BSAwesome {

	public static int getLessIndex(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1; // no exist
		}

		//只有一个元素
		if (arr.length == 1) {
			return 0;
		}

		//处理两端的情况
		if (arr[0] < arr[1]){
			return 0;
		}

		if (arr[arr.length - 1] < arr[arr.length - 2]) {
			return arr.length - 1;
		}
		int left = 1;
		int right = arr.length - 2;
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (arr[mid] > arr[mid - 1]) {
				right = mid - 1;
			} else if (arr[mid] > arr[mid + 1]) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return left;
	}

}
