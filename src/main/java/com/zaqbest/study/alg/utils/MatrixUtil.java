package com.zaqbest.study.alg.utils;

import java.util.Random;

public class MatrixUtil {
    public static int[][] generateRandomMatrix(int maxRow, int maxCol, int from, int to){
        int[][] ans = new int[maxRow][maxCol];
        Random random = new Random();
        for (int row = 0; row < maxRow; row++){
            for (int col = 0; col < maxCol; col++){
                ans[row][col] = random.nextInt(to - from) + from;
            }
        }

        return ans;
    }

    public static void printMatrix(int[][] matrix){
        if (matrix == null)
            return;
        for (int i = 0; i != matrix.length; i++) {
            if (i % 3 == 0) System.out.println();
            for (int j = 0; j != matrix[0].length; j++) {
                if (j % 3 == 0) System.out.print("  ");
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
