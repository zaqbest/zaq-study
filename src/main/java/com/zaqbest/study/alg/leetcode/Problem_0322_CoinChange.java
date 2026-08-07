package com.zaqbest.study.alg.leetcode;

import java.util.Arrays;

/**
 * 322:零钱兑换
 *
 * 问题描述
 * https://leetcode-cn.com/problems/coin-change/
 */
public class Problem_0322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.sort(coins);
        Arrays.fill(dp, amount+1);

        dp[0] = 0;
        for (int i = 1; i <= amount; i++){
            for (int j = 0; j < coins.length; j++){
                if (coins[j] <= i){
                    dp[i] = Math.min(dp[i], dp[i- coins[j]]+1);
                }
            }
        }

        return dp[amount] == amount+1 ? -1 : dp[amount];
    }
}
