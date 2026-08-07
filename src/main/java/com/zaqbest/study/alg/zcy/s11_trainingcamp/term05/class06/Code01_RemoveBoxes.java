package com.zaqbest.study.alg.zcy.s11_trainingcamp.term05.class06;

/**
 * 给出一些不同颜色的盒子boxes，盒子的颜色由不同的正数表示。
 *
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k>= 1），
 * 这样一轮之后你将得到 k * k 个积分。
 *
 * 返回 你能获得的最大积分和。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-boxes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 外部信息 简单化
 */
public class Code01_RemoveBoxes {

	public static int removeBoxes(int[] boxes) {
		int N = boxes.length;
		int[][][] dp = new int[N][N][N];
		int ans = process(boxes, 0, N - 1, 0, dp);
		return ans;
	}
	//boxes[L..R]，前面还跟着K个boxes[L]
	//前面的包袱和L..R所有数都消掉，最好得分是什么
	public static int process(int[] boxes, int L, int R, int K, int[][][] dp) {
		if (L > R) {
			return 0;
		}
		if (dp[L][R][K] > 0) {
			return dp[L][R][K];
		}
		int last = L; //last指定的最后一个相同的字符
		while (last + 1 <= R && boxes[last + 1] == boxes[L]) {
			last++;
		}
		int pre = K + last - L;
		int ans = (pre + 1) * (pre + 1) + process(boxes, last + 1, R, 0, dp);
		for (int i = last + 2; i <= R; i++) {
			if (boxes[i] == boxes[L] && boxes[i - 1] != boxes[L]) {
				ans = Math.max(ans, process(boxes, last + 1, i - 1, 0, dp) + process(boxes, i, R, pre + 1, dp));
			}
		}
		dp[L][R][K] = ans;
		return ans;
	}

}
