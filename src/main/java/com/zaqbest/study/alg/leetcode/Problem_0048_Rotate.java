package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.utils.ArrayUtil;

/**
 * 矩阵旋转90度
 * <p>
 * 问题描述
 * https://leetcode-cn.com/problems/rotate-image/
 * <p>
 * 算法描述
 * https://zhuanlan.zhihu.com/p/129333223
 * 关键词：找到每次旋转后的坐标
 * <p>
 * 对于一个m行n列的矩阵
 * 对于点，每次旋转90度
 * 1，(i,j) => (j, n-1-i)
 * 2，(j, n-1-i) => (n -1 -i, m - 1 -j)
 * 3,(n -1 -i, m - 1 -j) => (n - 1 - j, i)
 */
public class Problem_0048_Rotate {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("转换前：");
        ArrayUtil.printMatrix(matrix);

        new Problem_0048_Rotate().rotate(matrix);
        System.out.println("转换后：");
        ArrayUtil.printMatrix(matrix);
    }

    public void rotate(int[][] matrix) {
        //行数处理
        if (matrix == null || matrix.length == 0)
            return;
        //列数处理
        if (matrix[0] == null || matrix[0].length == 0)
            return;

        int m = matrix.length;
        int n = matrix[0].length;

        //注意!!!!
        // （m/2，（n+1)/2 边界情况的处理,仅且仅能取一条边
        //或者写成（m+1)/2, n/2
        for (int i = 0; i < m / 2; i++) {
            for (int j = 0; j < (n+1) / 2; j++) {
//        for (int i = 0; i < (m+1) / 2; i++) {
//            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][m - 1 - j];
                matrix[n - 1 - i][m - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }
}
