package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

public class Problem_0007_ReverseInteger {

	public static int reverse(int x) {
		boolean neg = ((x >>> 31) & 1) == 1;//最高位为1即为负数
		x = neg ? x : -x; //按照负数处理，因为负数比正数多了1个数
		int m = Integer.MIN_VALUE / 10;
		int o = Integer.MIN_VALUE % 10;
		int res = 0; //res一直是一个负数
		while (x != 0) {
			if (res < m  //乘10肯定溢出
					|| (res == m && x % 10 < o) //乘10再加上余数是否会溢出
			) {
				return 0;
			}
			res = res * 10 + x % 10;
			x /= 10;
		}
		return neg ? res : Math.abs(res);
	}

	public static int reverse_study(int x) {
		boolean neg = x < 0 ? true : false;
		x = neg ? x: -x;

		int m = Integer.MIN_VALUE  / 10;
		int n  = Integer.MIN_VALUE % 10;

		int res = 0;
		while (x != 0){
			if (res < m || (res == m && x % 10 < n)){
				return 0;
			}

			res = res * 10 + x % 10;
			x /= 10;
		}

		return neg ? res : -res;
	}

	public static void main(String[] args) {
		System.out.println(reverse(Integer.MIN_VALUE));
		System.out.println(reverse_study(Integer.MIN_VALUE));

		System.out.println(reverse(Integer.MAX_VALUE));
		System.out.println(reverse_study(Integer.MAX_VALUE));

		System.out.println(reverse(113123123));
		System.out.println(reverse_study(113123123));

	}

}
