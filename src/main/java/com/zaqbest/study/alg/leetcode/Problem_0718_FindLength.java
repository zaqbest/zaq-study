package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.zcy.s11_trainingcamp.term03.class03.Code05_LCSubstring;

/**
 * 718. 最长重复子数组
 *
 *  dp 行列对应模型
 *
 * 最长公共子串问题
 * {@link Code05_LCSubstring}
 */
public class Problem_0718_FindLength {
    public static int findLength(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length ==0 || nums2 == null || nums2.length == 0){
            return 0;
        }
        int N1 = nums1.length;
        int N2 = nums2.length;

        //dp[i][j]指的是num1[0...i]和num2[0..j]的最长公共子数组, 必须以num1[i]和num2[j]结尾
        //num1作为行，num2作为列
        int[][] dp = new int[N1][N2];
        int ans = 0;
        for (int i = 0; i < N1; i++){
            dp[i][0] = nums1[i] == nums2[0] ? 1 : 0;
            //收集答案
            ans = Math.max(ans, dp[i][0]);
        }

        for (int j = 0; j < N2; j++){
            dp[0][j] = nums1[0] == nums2[j] ? 1 : 0;
            //收集答案
            ans = Math.max(ans, dp[0][j]);
        }

        for(int i =1; i < N1; i++){
            for(int j = 1; j < N2; j++){

                if (nums1[i] == nums2[j]){
                    dp[i][j] = dp[i-1][j-1]+1;
                    //收集答案
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {0,1,1,1,1};
        int[] nums2 = {1,0,1,0,1};

        System.out.println(findLength(nums1, nums2));
    }
}
