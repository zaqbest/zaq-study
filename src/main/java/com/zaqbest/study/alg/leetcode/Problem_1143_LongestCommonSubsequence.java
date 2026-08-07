package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.zcy.s10_primary.class12.Code05_LongestSubsequence;
import com.zaqbest.study.alg.zcy.s11_trainingcamp.term03.class03.Code04_LCSubsequence;
import com.zaqbest.study.alg.zcy.s11_trainingcamp.term03.class03.Code05_LCSubstring;

/**
 * 1143. 最长公共子序列
 *
 * 最长公共子序列问题
 * {@link Code05_LongestSubsequence}
 * {@link Code04_LCSubsequence}
 *
 * 最长公共子串问题
 * {@link Code05_LCSubstring}
 *
 * 最小覆盖子串问题
 * {@link Problem_0076_MinWindow}
 */
public class Problem_1143_LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {

        return lcse(text1.toCharArray(), text2.toCharArray());
    }

    private static int lcse(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[str1.length - 1][str2.length - 1];
    }
}
