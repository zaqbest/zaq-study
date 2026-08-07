package com.zaqbest.study.alg.zcy.s30_great_offer.class10;

// 本题测试链接 : https://leetcode.cn/problems/jump-game-ii/
public class Code01_JumpGame {

	public static int jump(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int step = 0;
		int cur = 0; //当前step的情况下，可以到达的最大位置
		int next = 0; //如果多走一步，可以到达的最远位置
		for (int i = 0; i < arr.length; i++) {
			if (cur < i) {
				step++;
				cur = next;
			}
			next = Math.max(next, i + arr[i]);
		}
		return step;
	}

	public static void main(String[] args) {
		int[] arr = {2,3,1,1,4};

	}
}
