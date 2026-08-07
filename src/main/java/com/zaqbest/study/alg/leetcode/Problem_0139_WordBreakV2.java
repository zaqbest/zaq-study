package com.zaqbest.study.alg.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * 139: 单词拆分
 * 问题描述：https://leetcode-cn.com/problems/word-break/
 */
public class Problem_0139_WordBreakV2 {

    /**
     * 动态规划
     * dp[i] 表示（0， i-1)是否能够被分解
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len+1];
        Arrays.fill(dp, false);

        dp[0] = true;
        for (int i =1; i <=len; i++){
            for (int j = i -1; j >=0; j--){
                String sufix = s.substring(j, i);
                if (wordDict.contains(sufix) && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}
