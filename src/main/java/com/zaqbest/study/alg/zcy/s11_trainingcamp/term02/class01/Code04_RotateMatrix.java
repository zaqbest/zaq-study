package com.zaqbest.study.alg.zcy.s11_trainingcamp.term02.class01;

/**
 * 原地旋转正方形矩阵
 *
 * 关键词：宏观调度
 */
public class Code04_RotateMatrix {

	public static void rotate(int[][] matrix) {
		//左上角的点（Arow,Acol), 右下角的点为（Brow,Bcol)
		int Arow = 0;
		int Acol = 0;
		int Brow = matrix.length - 1;
		int Bcol = matrix[0].length - 1;
		while (Arow < Brow) {
			rotateEdge(matrix, Arow++, Acol++, Brow--, Bcol--);
		}
	}

	public static void rotateEdge(int[][] m, int Arow, int Acol, int Brow, int Bcol) {
		int tmp = 0;
		for (int i = 0; i < Bcol - Acol; i++) {
			tmp = m[Arow][Acol + i];
			m[Arow][Acol + i] = m[Brow - i][Acol];
			m[Brow - i][Acol] = m[Brow][Bcol - i];
			m[Brow][Bcol - i] = m[Arow + i][Bcol];
			m[Arow + i][Bcol] = tmp;
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.printf("%4d", matrix[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		printMatrix(matrix);
		rotate(matrix);
		System.out.println("=========");
		printMatrix(matrix);

	}

}
