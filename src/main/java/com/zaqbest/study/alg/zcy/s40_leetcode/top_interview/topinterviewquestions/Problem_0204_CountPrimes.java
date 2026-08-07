package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 给定正整数N, 计算1..N有多少个素数
 *
 * 思路
 * - 筛查法，常数时间的极致优化
 */
public class Problem_0204_CountPrimes {

	public static int countPrimes(int n) {
		if (n < 3) {
			return 0;
		}
		boolean[] f = new boolean[n];
		int count = n / 2; //偶数都不是素数
		// 只需要处理奇数位置
		for (int i = 3; i * i < n; i += 2) {
			//之前已经有因子划过×了，continue即可
			if (f[i]) {
				continue;
			}
			//[i+1,i*i-1]都已经之前判断过了
			for (int j = i * i; j < n; j += 2 * i) {
				if (!f[j]) {
					--count;
					f[j] = true;
				}
			}
		}
		return count;
	}

}
