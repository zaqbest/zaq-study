package com.zaqbest.study.alg.zcy.s11_trainingcamp.term02.class01;

/**
 * 转圈打印矩阵
 *
 * 关键词：宏观调度
 */
public class Code05_PrintMatrixSpiralOrder {

	public static void spiralOrderPrint(int[][] matrix) {
		int Arow = 0;
		int Acol = 0;
		int Brow = matrix.length - 1;
		int Bcol = matrix[0].length - 1;
		while (Arow <= Brow && Acol <= Bcol) {
			printEdge(matrix, Arow++, Acol++, Brow--, Bcol--);
		}
	}

	public static void printEdge(int[][] m, int Arow, int Acol, int Brow, int Bcol) {
		if (Arow == Brow) {
			for (int i = Acol; i <= Bcol; i++) {
				System.out.print(m[Arow][i] + " ");
			}
		} else if (Acol == Bcol) {
			for (int i = Arow; i <= Brow; i++) {
				System.out.print(m[i][Acol] + " ");
			}
		} else {
			int curR = Arow;
			int curC = Acol;
			while (curC != Bcol) {
				System.out.print(m[Arow][curC] + " ");
				curC++;
			}
			while (curR != Brow) {
				System.out.print(m[curR][Bcol] + " ");
				curR++;
			}
			while (curC != Acol) {
				System.out.print(m[Brow][curC] + " ");
				curC--;
			}
			while (curR != Arow) {
				System.out.print(m[curR][Acol] + " ");
				curR--;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);

	}

}
