package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

public class Problem_0041_FirstMissingPositive {
    public static int firstMissingPositive(int[] arr) {
		int l = 0;
		int r = arr.length;
		while (l < r) {
			if (arr[l] == l + 1) {
				l++;
			} else if (arr[l] <= l || arr[l] > r  //低于最小值，大于最大值得值不需要
					|| arr[arr[l] - 1] == arr[l]) {
				// arr[arr[l]-1] == arr[l]
				// 3 -> 8
				// 7 -> 8
				// 如果两个数字是重复的也是不需要的
				swap(arr,l,--r);
			} else {
				swap(arr, l, arr[l] - 1);
			}
		}
		return l + 1;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
