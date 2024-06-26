package com.zaqbest.study.basics.algorithm.leetcode;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 */
public class Problem_0300_LengthOfLIS {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int res = 1;
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    res = Math.max(res, dp[i]);
                }
            }
        }

        return res;
    }

}
