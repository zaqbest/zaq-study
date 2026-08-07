package com.zaqbest.study.alg.zcy.s11_trainingcamp.term05.class06;

/**
 * 有台奇怪的打印机有以下两个特殊要求：
 *
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在从起始到结束的任意位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 *
 * 示例 1：
 * 输入：s = "aaabbb"
 * 输出：2
 * 解释：首先打印 "aaa" 然后打印 "bbb"。
 * 示例 2：
 *
 * 输入：s = "aba"
 * 输出：2
 * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/strange-printer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code02_StrangePrinter {

	public static int strangePrinter(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		// dp[L][R] 指的是从L到R需要多少转
		int[][] dp = new int[N][N];
		dp[N - 1][N - 1] = 1;
		for (int i = 0; i < N - 1; i++) {
			dp[i][i] = 1;//只有一个数，肯定是一转
			dp[i][i + 1] = str[i] == str[i + 1] ? 1 : 2; //两个数，一样就是一转；不一样就是两转
		}
		for (int L = N - 3; L >= 0; L--) {
			for (int R = L + 2; R < N; R++) {

				// L....R, 最差可能性是每个数字都不一样，为所有数字的个数；最差解
				dp[L][R] = R - L + 1;

				// L...k-1 k...R，k为右部分的开始位置
				for (int k = L + 1; k <= R; k++) {
					dp[L][R] = Math.min(
							dp[L][R],
							dp[L][k - 1] + dp[k][R]
									- (str[L] == str[k] ? 1 : 0) //如何str[L] == str[k]，可以用一转得到
					);
				}
			}
		}
		return dp[0][N - 1];
	}

}
