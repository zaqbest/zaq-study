package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class01;

import com.zaqbest.study.alg.utils.MatrixUtil;

import java.util.TreeSet;

public class Code03_MaxSumofRectangleNoLargerThanK_Study {
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix[0] == null){
            return 0;
        }

        int rows = matrix.length, cols = matrix[0].length, ans = Integer.MIN_VALUE;
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int s = 0; s < rows; s++){
            int[] colSum = new int[cols];
            for (int e = s; e < rows; e++){

                treeSet.clear();
                treeSet.add(0);
                int rowSum = 0;
                for (int col = 0; col < cols; col++){

                    colSum[col] += matrix[e][col];
                    rowSum += colSum[col];

                    Integer it = treeSet.ceiling(rowSum -k);
                    if (it != null){
                        ans = Math.max(ans, rowSum -it);
                    }
                    treeSet.add(rowSum);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[][] m = MatrixUtil.generateRandomMatrix(10, 10, 1, 100);
            int ans1 = maxSumSubmatrix(m, 50);
            int ans2 = Code03_MaxSumofRectangleNoLargerThanK.maxSumSubmatrix(m, 50);

            if (ans1 != ans2){
                System.out.println("oops!");
                MatrixUtil.printMatrix(m);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }

        System.out.println("finish!");
    }
}
