package com.zaqbest.study.alg.leetcode;

/**
 * 0036  有效的数独
 */
public class Problem_0036_IsValidSudoku_Solvable {

    public static boolean isValidSudoku(char[][] board){
        return backtrack(board, 0, 0);
    }

    private static boolean backtrack(char[][] board, int r, int c) {
        int m = 9, n = 9;
        if (c == n) {
            // 穷举到最后一列的话就换到下一行重新开始。
            return backtrack(board, r + 1, 0);
        }
        if (r == m) {
            // 找到一个可行解，触发 base case
            return true;
        }
        // 就是对每个位置进行穷举
        for (int i = r; i < m; i++) {
            for (int j = c; j < n; j++) {

                if (board[i][j] != '.') {
                    // 如果有预设数字，不用我们穷举
                    return backtrack(board, i, j + 1);
                }

                for (char ch = '1'; ch <= '9'; ch++) {
                    // 如果遇到不合法的数字，就跳过
                    if (!isValid(board, i, j, ch))
                        continue;
                    //先设置为ch,看后续是否可以完成
                    board[i][j] = ch;
                    // 如果找到一个可行解，立即结束
                    if (backtrack(board, i, j + 1)) {
                        return true;
                    }
                    //ch不能完成，恢复线程
                    board[i][j] = '.';
                }
                // 穷举完 1~9，依然没有找到可行解，此路不通
                return false;
            }
        }
        return false;
    }

    // 判断 board[i][j] 是否可以填入 n
    private static boolean isValid(char[][] board, int r, int c, char n) {
        for (int i = 0; i < 9; i++) {
            // 判断行是否存在重复
            if (board[r][i] == n) return false;
            // 判断列是否存在重复
            if (board[i][c] == n) return false;
            // 判断 3 x 3 方框是否存在重复
            if (board[(r/3)*3 + i/3][(c/3)*3 + i%3] == n)
                return false;
        }
        return true;
    }

    private static void printMatrix(char[][] matrix) {
        if (matrix == null)
            return;
        for (int i = 0; i != matrix.length; i++) {
            if (i == 3 || i == 6) System.out.println();
            for (int j = 0; j != matrix[0].length; j++) {
                if (j == 3 || j == 6) System.out.print("  ");
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] m4 = {
                {'.','.','2',  '.','9','5',  '.','.','.'},
                {'8','.','.',  '7','3','.',  '.','.','.'},
                {'.','.','.',  '4','2','.',  '1','3','.'},

                {'4','.','.',  '.','.','.',  '7','.','9'},
                {'.','.','.',  '3','.','.',  '.','.','.'},
                {'1','.','6',  '.','.','.',  '.','4','.'},

                {'.','.','.',  '.','.','4',  '2','.','.'},
                {'.','.','9',  '.','1','.',  '.','.','.'},
                {'7','.','5',  '.','.','.',  '.','.','6'}};

        char[][] m5 = {
                {'.','8','7','6','5','4','3','2','1'},
                {'2','.','.','.','.','.','.','.','.'},
                {'3','.','.','.','.','.','.','.','.'},
                {'4','.','.','.','.','.','.','.','.'},
                {'5','.','.','.','.','.','.','.','.'},
                {'6','.','.','.','.','.','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'8','.','.','.','.','.','.','.','.'},
                {'9','.','.','.','.','.','.','.','.'}};

        Problem_0036_IsValidSudoku_Solvable solution = new Problem_0036_IsValidSudoku_Solvable();
        solution.isValidSudoku(m5);
        solution.printMatrix(m5);
    }
}
