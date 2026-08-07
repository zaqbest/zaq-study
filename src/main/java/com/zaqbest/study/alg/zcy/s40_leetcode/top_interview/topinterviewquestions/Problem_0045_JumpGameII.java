package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

public class Problem_0045_JumpGameII {
	public static int jump(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int step = 0;
		int cur = 0; //再走step的时候，最远可以走到的位置
		int next = arr[0]; //如果再走一步，能到达的最远位置
		for (int i = 1; i < arr.length; i++) {
//            if(next >= arr.length - 1){
//                return step + 1;
//            }
			if (cur < i) {
				step++;
				cur = next;
			}
			next = Math.max(next, i + arr[i]);
		}
		return step;
	}
}
