package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class08;

/**
 * 汉诺塔游戏的要求把所有的圆盘从左边都移到右边的柱子上，给定一个整型数组arr，
 * 其中只含有1、2和3，代表所有圆盘目前的状态，1代表左柱，2代表中柱，3代表右柱，
 * arr[i]的值代表第i+1个圆盘的位置。 比如，arr=[3,3,2,1]，
 * 代表第1个圆盘在右柱上、第2个圆盘在右柱上、第3个圆盘在中 柱上、第4个圆盘在左柱上
 * 如果arr代表的状态是最优移动轨迹过程中出现的状态，返回arr这种状态是最优移动轨迹中的第几个状态;
 * 如果arr代表的状态不是最优移动轨迹过程中出现的状态，则返回- 1。
 *
 * 补充知识
 * 汉诺塔 最优步数为 2^n-1
 *
 * 总流程
 * 1）1..N-1 从 from->other
 * 2) N 从 from -> to
 * 3) 1..N-1 从other->to
 */
public class Code04_HanoiProblem {

	public static int step1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		return process(arr, arr.length - 1, 1, 2, 3);
	}

	// 目标是:  把0~i的圆盘，从from全部挪到to上
	// 返回，根据arr中的状态arr[0..i]，它是最优解的第几步？
	// f(i, 3 , 2, 1)    f(i, 1, 3, 2)    f(i, 3, 1, 2)
	public static int process(int[] arr, int i, int from, int other, int to) {
		if (i == -1) {
			return 0;
		}
		if (arr[i] != from && arr[i] != to) {
			return -1;
		}
		if (arr[i] == from) { // 第一大步没走完
			return process(arr, i - 1, from, to, other);
		} else { // arr[i] == to
			// 已经走完1，2两步了，
			int rest = process(arr, i - 1, other, from, to); // 第三大步完成的程度
			if (rest == -1) {
				return -1;
			}
			//1)0..i-1 从 from->other, 2^i-1
			//2)i from->to, 需要一步
			// 总共需要 2^i-1+1
			return (1 << i) + rest;
		}
	}

	public static int step2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int from = 1;
		int mid = 2;
		int to = 3;
		int i = arr.length - 1;
		int res = 0;
		int tmp = 0;
		while (i >= 0) {
			if (arr[i] != from && arr[i] != to) {
				return -1;
			}
			if (arr[i] == to) {
				res += 1 << i;
				tmp = from;
				from = mid;
			} else {
				tmp = to;
				to = mid;
			}
			mid = tmp;
			i--;
		}
		return res;
	}

	public static void main(String[] args) {

		int[] arr = { 3, 3, 2, 1 };
		System.out.println(step1(arr));
		System.out.println(step2(arr));

	}
}
