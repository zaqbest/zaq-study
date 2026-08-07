package com.zaqbest.study.alg.zcy.s30_great_offer.class01;

import java.util.Arrays;

/**
 * 给定一个有序数组arr，代表坐落在X轴上的点，给定一个正数K，代表绳子的长度，返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 *
 * 思路
 * - 滑动窗口
 */
public class Code01_CordCoverMaxPoint {

	/**
	 * 方法1（二分）
	 * 时间复杂度O(N*logN)
	 *
	 * @param arr
	 * @param L
	 * @return
	 */
	public static int maxPoint1(int[] arr, int L) {
		int res = 1;
		for (int i = 0; i < arr.length; i++) {
			int nearest = nearestIndex(arr, i, arr[i] - L);
			res = Math.max(res, i - nearest + 1);
		}
		return res;
	}

	public static int nearestIndex(int[] arr, int R, int value) {
		int L = 0;
		int index = R;
		while (L <= R) {
			int mid = L + ((R - L) >> 1);
			if (arr[mid] >= value) {
				index = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return index;
	}

	/**
	 * 滑动窗口
	 * 时间复杂度 O(N)
	 *
	 * @param arr
	 * @param L
	 * @return
	 */
	public static int maxPoint2(int[] arr, int L) {
		int left = 0;
		int right = 0;
		int N = arr.length;
		int max = 0;
		while (left < N) {
			while (right < N && arr[right] - arr[left] <= L) {
				right++;
			}
			// 如果从while里跳出来，说明right = N 或者 [right] - [left] > L
			// right来到第一个不合法位置
			max = Math.max(max, right - left);
			left++;
		}
		return max;
	}

	// for test
	public static int test(int[] arr, int L) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			int pre = i - 1;
			while (pre >= 0 && arr[i] - arr[pre] <= L) {
				pre--;
			}
			max = Math.max(max, i - pre);
		}
		return max;
	}

	// for test
	public static int[] generateArray(int len, int max) {
		int[] ans = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * max);
		}
		Arrays.sort(ans);
		return ans;
	}


	public static void main(String[] args) {
		int[] arr = {1,2,5,6,7,11};
		int L = 3;
		int res = maxPoint2(arr, L);
		System.out.println(res);
	}

	public static void main0(String[] args) {
		int len = 100;
		int max = 1000;
		int testTime = 100000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int L = (int) (Math.random() * max);
			int[] arr = generateArray(len, max);
			int ans1 = maxPoint1(arr, L);
			int ans2 = maxPoint2(arr, L);
			int ans3 = test(arr, L);
			if (ans1 != ans2 || ans2 != ans3) {
				System.out.println("oops!");
				break;
			}
		}

	}

}
