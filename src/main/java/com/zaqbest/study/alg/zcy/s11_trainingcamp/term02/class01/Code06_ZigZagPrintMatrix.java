package com.zaqbest.study.alg.zcy.s11_trainingcamp.term02.class01;

/**
 * Z字形打印矩阵
 *
 * 关键词：宏观调度
 */
public class Code06_ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		int Arow = 0;
		int Acol = 0;
		int Brow = 0;
		int Bcol = 0;
		int endRow = matrix.length - 1;
		int endCol = matrix[0].length - 1;
		boolean fromUp = false;
		while (Arow != endRow + 1) {
			printLevel(matrix, Arow, Acol, Brow, Bcol, fromUp);
			Arow = Acol == endCol ? Arow + 1 : Arow;
			Acol = Acol == endCol ? Acol : Acol + 1;
			Bcol = Brow == endRow ? Bcol + 1 : Bcol;
			Brow = Brow == endRow ? Brow : Brow + 1;
			fromUp = !fromUp;
		}
		System.out.println();
	}

	public static void printLevel(int[][] m, int Arow, int Acol, int Brow, int Bcol,
			boolean fromUp) {
		if (fromUp) {
			while (Arow != Brow + 1) {
				System.out.print(m[Arow++][Acol--] + " ");
			}
		} else {
			while (Brow != Arow - 1) {
				System.out.print(m[Brow--][Bcol++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
