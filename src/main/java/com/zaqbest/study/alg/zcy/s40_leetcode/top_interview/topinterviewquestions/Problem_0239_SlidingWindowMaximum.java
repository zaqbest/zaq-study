package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

import java.util.LinkedList;

/**
 * 滑动窗口最大值
 */
public class Problem_0239_SlidingWindowMaximum {

	public static int[] maxSlidingWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		for (int R = 0; R < arr.length; R++) {
			//发现更大的数，淘汰笔[R]小的数字
			while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
				qmax.pollLast();
			}
			qmax.addLast(R);
			//最大值划出窗口
			if (qmax.peekFirst() == R - w) {
				qmax.pollFirst();
			}
			//收集答案
			if (R >= w - 1) {
				res[index++] = arr[qmax.peekFirst()];
			}
		}
		return res;
	}

}
