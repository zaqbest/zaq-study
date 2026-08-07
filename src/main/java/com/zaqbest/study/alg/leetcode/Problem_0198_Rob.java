package com.zaqbest.study.alg.leetcode;

/**
 * 198:打家劫舍
 *
 * 核心算法：动态规划 dp[i] = max(dp[i-2] + nums[i], dp[i-1])
 */
public class Problem_0198_Rob {
    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        else if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        else {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(dp[0], nums[1]);
            for (int i = 2; i < nums.length; i++){
                dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
            }
            return dp[nums.length-1];
        }
    }
}
