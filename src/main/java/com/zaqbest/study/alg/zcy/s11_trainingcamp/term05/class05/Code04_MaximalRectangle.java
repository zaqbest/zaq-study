package com.zaqbest.study.alg.zcy.s11_trainingcamp.term05.class05;

import java.util.Stack;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1，
 * 其中，
 * 内部全是1的所有子矩阵中，含有最多1的子矩阵中，含有几个1？
 *
 * 笔记
 * 前置知识
 * - 单调栈
 * - 最大矩形面积
 *
 * 处理思路
 * - 二维压缩成一维处理
 */
public class Code04_MaximalRectangle {

	public static int maxRecSize(int[][] map) {
		if (map == null || map.length == 0 || map[0].length == 0) {
			return 0;
		}
		int maxArea = 0;
		int[] height = new int[map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
			}
			maxArea = Math.max(maxRecFromBottom(height), maxArea);
		}
		return maxArea;
	}

	// height是正方图数组
	public static int maxRecFromBottom(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int maxArea = 0;
		// 左右两侧离最近，小的 ,,,,  底  -> 顶        小   ->  大
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
				int j = stack.pop(); // 结算哪个位置的答案
				int k = stack.isEmpty() ? -1 : stack.peek(); // 左边不能扩到的位置
				int curArea = (i - k - 1) * height[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int j = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length - k - 1) * height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}

	public static void main(String[] args) {
		int[][] map = { { 1, 0, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 0 }, };
		System.out.println(maxRecSize(map));
	}

}
