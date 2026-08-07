package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class01;

/**
 * 给定一个二维数组matrix，可以从任何位置出发，每一步可以走向上、下、左、右，四个方向。返回最大递增链的长度。
 * 例子：
 * matrix =
 * 5  4  3
 * 3  1  2
 * 2  1  3
 * 从最中心的1出发，是可以走出1 2 3 4 5的链的，而且这是最长的递增链。所以返回长度5
 *
 * 思路： 暴力递归，dp
 */
public class Code01_LongestIncreasingPath_Study {
	public static int maxPath(int[][] matrix) {
		int ans = Integer.MIN_VALUE;

		int R = matrix.length;
		int C = matrix[0].length;

		for (int r = 0; r < R; r++){
			for (int c = 0; c < C; c++){
				ans = Math.max(ans, process(matrix, r, c));
			}
		}

		return ans;
	}

	private static int process(int[][] matrix, int row, int col){
		if (row < 0 || row > matrix.length - 1 || col < 0 || col > matrix[0].length - 1){
			return -1;
		}

		int up =0;
		int down = 0;
		int left=0;
		int right=0;
		if (row > 0 && matrix[row-1][col] > matrix[row][col]){
			up = process(matrix, row-1, col);
		}

		if (row < matrix.length - 1 && matrix[row+1][col] > matrix[row][col]){
			down = process(matrix, row+1, col);
		}

		if (col > 0 && matrix[row][col-1] > matrix[row][col]){
			left = process(matrix, row, col-1);
		}

		if (col < matrix[0].length - 1 && matrix[row][col+1] > matrix[row][col]){
			right = process(matrix, row, col+1);
		}

		return 1 + Math.max(Math.max(up, down), Math.max(left, right));
	}

	public static int maxPath_dp(int[][] matrix) {
		int ans = Integer.MIN_VALUE;

		int R = matrix.length;
		int C = matrix[0].length;
		int[][] dp = new int[R][C];

		for (int r = 0; r < R; r++){
			for (int c = 0; c < C; c++){
				ans = Math.max(ans, process_dp(matrix, r, c, dp));
			}
		}

		return ans;
	}

	private static int process_dp(int[][] matrix, int row, int col, int[][] dp){
		if (row < 0 || row > matrix.length - 1 || col < 0 || col > matrix[0].length - 1){
			return -1;
		}

		int up =0;
		int down = 0;
		int left=0;
		int right=0;

		if (dp[row][col] != 0){
			return dp[row][col];
		}

		if (row > 0 && matrix[row-1][col] > matrix[row][col]){
			up = process(matrix, row-1, col);
		}

		if (row < matrix.length - 1 && matrix[row+1][col] > matrix[row][col]){
			down = process(matrix, row+1, col);
		}

		if (col > 0 && matrix[row][col-1] > matrix[row][col]){
			left = process(matrix, row, col-1);
		}

		if (col < matrix[0].length - 1 && matrix[row][col+1] > matrix[row][col]){
			right = process(matrix, row, col+1);
		}

		int ans =  1 + Math.max(Math.max(up, down), Math.max(left, right));
		dp[row][col] = ans;

		return ans;
	}


	public static void main(String[] args) {

		int[][] m = {{5,  4,  3},{3,  1,  2},{2,  1,  3}};
		int res = maxPath(m);
		int res2 = maxPath_dp(m);
		System.out.println(res);
		System.out.println(res2);
	}
}
