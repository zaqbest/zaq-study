package com.zaqbest.study.basics.algorithm.zcy.s11_trainingcamp.term03.class05;

/**
 * 编辑距离问题
 *
 * 给定两个字符串str1和str2，再给定三个整数ic、dc和rc，分别代表插入、删 除和替换一个字符的代价，返回将str1编辑成str2的最小代价。
 * 【举例】
 * str1="abc"，str2="adc"，ic=5，dc=3，rc=2 从"abc"编辑成"adc"，把'b'替换成'd'是代价最小的，所以返回2
 * str1="abc"，str2="adc"，ic=5，dc=3，rc=100 从"abc"编辑成"adc"，先删除'b'，然后插入'd'是代价最小的，所以返回8
 * str1="abc"，str2="abc"，ic=5，dc=3，rc=2 不用编辑了，本来就是一样的字符串，所以返回0
 */
public class Code02_EditCost {

	/**
	 *
	 * @param s1
	 * @param s2
	 * @param ic 插入成本
	 * @param dc 删除成本
	 * @param rc 替换成本
	 * @return
	 */
	public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length + 1;
		int M = str2.length + 1;
		// dp[i[j]指的是str1[0...i]字符，转换成str2[0...j]字符的最小成本
		int[][] dp = new int[N][M];
		//初始化列
		for (int i = 1; i < N; i++) {
			dp[i][0] = dc * i;
		}
		//初始化行
		for (int j = 1; j < M; j++) {
			dp[0][j] = ic * j;
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if (str1[i - 1] == str2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];//如果最后一个字符相同，则只要看前一个结果
				} else {
					dp[i][j] = dp[i - 1][j - 1] + rc;//如果最后一个不一样，则替换最后一个字符即可
				}
				dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);//str1[0...i]转换成str2[0...j-1],最后在增加一个字符即可
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);//str1[0...i-1]就可以转换成str2[0...j]字符，把str1的最后一个字符删除即可
			}
		}
		return dp[N - 1][M - 1];
	}

	public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
		char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
		if (chs1.length < chs2.length) {
			int tmp = ic;
			ic = dc;
			dc = tmp;
		}
		int[] dp = new int[shorts.length + 1];
		for (int i = 1; i <= shorts.length; i++) {
			dp[i] = ic * i;
		}
		for (int i = 1; i <= longs.length; i++) {
			int pre = dp[0];
			dp[0] = dc * i;
			for (int j = 1; j <= shorts.length; j++) {
				int tmp = dp[j];
				if (longs[i - 1] == shorts[j - 1]) {
					dp[j] = pre;
				} else {
					dp[j] = pre + rc;
				}
				dp[j] = Math.min(dp[j], dp[j - 1] + ic);
				dp[j] = Math.min(dp[j], tmp + dc);
				pre = tmp;
			}
		}
		return dp[shorts.length];
	}

	public static void main(String[] args) {
		String str1 = "ab12cd3";
		String str2 = "abcdf";
		System.out.println(minCost1(str1, str2, 5, 3, 2));
		System.out.println(minCost2(str1, str2, 5, 3, 2));

		str1 = "abcdf";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 3, 2, 4));
		System.out.println(minCost2(str1, str2, 3, 2, 4));

		str1 = "";
		str2 = "ab12cd3";
		System.out.println(minCost1(str1, str2, 1, 7, 5));
		System.out.println(minCost2(str1, str2, 1, 7, 5));

		str1 = "abcdf";
		str2 = "";
		System.out.println(minCost1(str1, str2, 2, 9, 8));
		System.out.println(minCost2(str1, str2, 2, 9, 8));

	}

}
