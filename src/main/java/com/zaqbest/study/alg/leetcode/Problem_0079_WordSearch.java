package com.zaqbest.study.alg.leetcode;

/**
 * 79:单词搜索
 * 问题描述：https://leetcode-cn.com/problems/word-search/
 *
 * 算法描述
 * 深度优先搜索
 * https://leetcode-cn.com/problems/word-search/solution/shen-du-you-xian-sou-suo-yu-hui-su-xiang-jie-by-ja/
 */
public class Problem_0079_WordSearch {
    //定义四个方向
    int[][] direct = new int[][]{{0,1},{1,0},{-1, 0},{0, -1}};

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        int[][] mark = new int[m][n];

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == word.toCharArray()[0]){
                    mark[i][j] = 1;
                    if (backtrace(board, i, j, mark, word.substring(1))){
                        return true;
                    } else {
                        //进行回溯!!!!
                        mark[i][j] = 0;
                    }
                }
            }
        }

        return false;
    }

    boolean backtrace(char[][] board, int i, int j, int[][] mark, String word){
        if (word.length() == 0)
            return true;

        //分别遍历四个方向!!!
        for (int[] d: direct){
            int currI = i + d[0];
            int currJ = j + d[1];

            if (currI >=0 && currI < board.length
                    && currJ >= 0 && currJ < board[0].length
                    && board[currI][currJ] == word.toCharArray()[0]){
                //已经被使用过
                if (mark[currI][currJ] == 1){
                    continue;
                } else{
                    mark[currI][currJ] = 1;
                    if (backtrace(board, currI, currJ, mark, word.substring(1))){
                        return true;
                    }else {
                        mark[currI][currJ] = 0;
                    }
                }
            }
        }

        return false;
    }
}
