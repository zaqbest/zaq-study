package com.zaqbest.study.alg.leetcode;

/**
 * 240.搜索二维矩阵
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem_0240_SearchMatrix {

    public static void main(String[] args) {
        int [][] matrix = new int[][]{
                {1,4,7,11,15}
                ,{2,5,8,12,19}
                ,{3,6,9,16,22}
                ,{10,13,14,17,24}
                ,{18,21,23,26,30}
        };
        int target = 13;

        boolean res = searchMatrix(matrix, target);
        System.out.println(res);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int line = m-1;
        int col = 0;

        while (line >=0 && col < n) {
            int curr_val = matrix[line][col];
            System.out.println(curr_val);
            if (target == curr_val) {
                return true;
            } else if (curr_val > target){
                line--;
            } else {
                col++;
            }
        }
        return false;
    }
}
