package com.zaqbest.study.alg.zcy.s30_great_offer.class18;

/**
 * 最大路径和
 *
 * 测试链接： https://www.nowcoder.com/questionTerminal/8ecfe02124674e908b2aae65aad4efdf
 */

public class Code03_CherryPickup {

//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int N = sc.nextInt();
//		int M = sc.nextInt();
//		int[][] matrix = new int[N][M];
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				matrix[i][j] = sc.nextInt();
//			}
//		}
//		int ans = cherryPickup1(matrix);
//		System.out.println(ans);
//		sc.close();
//	}

	public static void main(String[] args) {
		int[][] m1 = new int[][]{
				{1,1,1,1,1,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,1},
				{1,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,1,1,1,1,1,1},
		};

		int[][] m2 = new int[][]{
				{1,1,1,1,1,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,0},
				{0,0,0,0,1,0,0,0,0,1},
				{1,0,0,0,1,0,0,0,1,0},
				{0,0,0,0,1,1,1,1,0,1},
		};

		int res1 = cherryPickup1(m1);
		int res2 = cherryPickup2(m1);

		System.out.println(res1);
		System.out.println(res2);

		res1 = cherryPickup1(m2);
		res2 = cherryPickup2(m2);
		System.out.println(res1);
		System.out.println(res2);
	}

	/**
	 * 递归版本， 更易于理解
	 * 思路： 从左上角开始有A（a,b), B(c,d)两个人, 分别向下或者向右走；
	 * 如果走到同一个格子，数组只算一份
	 *
	 * @param grid
	 * @return
	 */
	public static int cherryPickup1(int[][] grid){
		return process1(grid, 0, 0, 0, 0);
	}

	private static int process1(int[][] grid, int a, int b, int c, int d) {
		int N = grid.length;
		int M =grid[0].length;

		//右下的格子
		if ((a == N -1 && b == M -1) && (c == N -1 && d == M -1)){
			return grid[a][b];
		}

		int next = 0;
		//A 下， B 下
		if (a < N -1 && c < N - 1){
			next = Math.max(next, process1(grid, a + 1, b, c + 1, d));
		}
		//A 右， B 右
		if (b < M -1 && d < M -1){
			next = Math.max(next, process1(grid, a, b+1, c, d+1));
		}

		//A 下， B 右
		if (a < N -1 && d < M -1){
			next = Math.max(next, process1(grid, a + 1, b , c, d+1));
		}

		//A 右， B 下
		if (b < M -1 && c < N - 1){
			next = Math.max(next, process1(grid, a, b+1 , c+1, d));
		}

		int ans = next;
		//加上当前位置
		if (a == c && b == d){
			ans += grid[a][b];
		} else {
			ans = ans + grid[a][b] + grid[c][d];
		}

		return ans;
	}


	public static int cherryPickup2(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		int[][][] dp = new int[N][M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					dp[i][j][k] = Integer.MIN_VALUE;
				}
			}
		}
		int ans = process2(grid, 0, 0, 0, dp);
		return ans < 0 ? 0 : ans;
	}

	public static int process2(int[][] grid, int x1, int y1, int x2, int[][][] dp) {
		if (x1 == grid.length || y1 == grid[0].length || x2 == grid.length || x1 + y1 - x2 == grid[0].length) {
			return Integer.MIN_VALUE;
		}
		if (dp[x1][y1][x2] != Integer.MIN_VALUE) {
			return dp[x1][y1][x2];
		}
		if (x1 == grid.length - 1 && y1 == grid[0].length - 1) {
			dp[x1][y1][x2] = grid[x1][y1];
			return dp[x1][y1][x2];
		}
		int next = Integer.MIN_VALUE;
		next = Math.max(next, process2(grid, x1 + 1, y1, x2 + 1, dp));
		next = Math.max(next, process2(grid, x1 + 1, y1, x2, dp));
		next = Math.max(next, process2(grid, x1, y1 + 1, x2, dp));
		next = Math.max(next, process2(grid, x1, y1 + 1, x2 + 1, dp));
		if (grid[x1][y1] == -1 || grid[x2][x1 + y1 - x2] == -1 || next == -1) {
			dp[x1][y1][x2] = -1;
			return dp[x1][y1][x2];
		}
		if (x1 == x2) {
			dp[x1][y1][x2] = grid[x1][y1] + next;
			return dp[x1][y1][x2];
		}
		dp[x1][y1][x2] = grid[x1][y1] + grid[x2][x1 + y1 - x2] + next;
		return dp[x1][y1][x2];
	}

}