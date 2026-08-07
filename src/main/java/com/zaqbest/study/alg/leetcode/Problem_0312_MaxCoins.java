package com.zaqbest.study.alg.leetcode;

/**
 * 312 戳气球问题
 */
public class Problem_0312_MaxCoins {
    public static int maxCoins(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int N = arr.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            help[i + 1] = arr[i];
        }

        int[][] dp = new int[N+1][N+1];
        for(int i = 0; i <= N; i++){
            for (int j = 0; j <=N; j++){
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        return process_dp(help, 1, N, dp);
    }

    // 打爆arr[L..R]范围上的所有气球，返回最大的分数
    // 假设arr[L-1]和arr[R+1]一定没有被打爆
    public static int process_dp(int[] arr, int L, int R, int[][] dp) {

        if (dp[L][R] > Integer.MIN_VALUE){
            return dp[L][R];
        }

        if (L == R) {// 如果arr[L..R]范围上只有一个气球，直接打爆即可
            return arr[L - 1] * arr[L] * arr[R + 1];
        }
        // 最后打爆arr[L]的方案，和最后打爆arr[R]的方案，先比较一下
        int max = Math.max(
                arr[L - 1] * arr[L] * arr[R + 1] + process_dp(arr, L + 1, R, dp),
                arr[L - 1] * arr[R] * arr[R + 1] + process_dp(arr, L, R - 1, dp));
        // 尝试中间位置的气球最后被打爆的每一种方案
        for (int i = L + 1; i < R; i++) {
            max = Math.max(max,
                    arr[L - 1] * arr[i] * arr[R + 1] + process_dp(arr, L, i - 1, dp)
                            + process_dp(arr, i + 1, R, dp));
        }

        dp[L][R] = max;

        return max;
    }
}
