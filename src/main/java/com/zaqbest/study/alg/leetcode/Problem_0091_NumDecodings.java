package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.zcy.s10_primary.class11.Code06_ConvertToLetterString;

/**
 * 0091 解码方法
 *
 * 暴力递归 记忆化搜索
 *
 * {@link Code06_ConvertToLetterString}
 */
public class Problem_0091_NumDecodings {
    public static int numDecodings1(String s) {
        return process1(s.toCharArray(), 0);
    }

    /**
     * 返回chars[i...]总共有多少种方法
     *
     * @param str
     * @param i 当前处理的i
     * @return
     */
    private static int process1(char[] str, int i){
        if (i == str.length){
            return 1;
        }

        //非法位置
        if (str[i] == '0'){
            return 0;
        }

        if (str[i] == '1'){
            int res = process1(str, i+1);

            if (i+1 < str.length){
                res += process1(str, i+2);
            }

            return res;
        }

        if (str[i] == '2'){
            int res = process1(str, i+1);

            if(i+1 < str.length && (str[i+1] >= '0' && str[i+1]<='6')){
                res += process1(str, i+2);
            }

            return res;
        }

        return process1(str, i+1);
    }


    public static int numDecodings2(String s) {
        int[] dp = new int[s.length()+1];
        for (int i = 0; i < s.length()+1; i++){
            dp[i] = Integer.MIN_VALUE;
        }
        return process2(s.toCharArray(), 0, dp);
    }

    /**
     * 返回chars[i...]总共有多少种方法
     *
     * @param str
     * @param i 当前处理的i
     * @return
     */
    private static int process2(char[] str, int i, int[] dp){

        if (dp[i] > Integer.MIN_VALUE){
            return dp[i];
        }

        if (i == str.length){
            dp[i] = 1;
            return 1;
        }

        //非法位置
        if (str[i] == '0'){
            dp[i] = 0;
            return 0;
        }

        if (str[i] == '1'){
            int res = process2(str, i+1, dp);

            if (i+1 < str.length){
                res += process2(str, i+2, dp);
            }
            dp[i] = res;
            return res;
        }

        if (str[i] == '2'){
            int res = process2(str, i+1, dp);

            if(i+1 < str.length && (str[i+1] >= '0' && str[i+1] <= '6')){
                res += process2(str, i+2, dp);
            }

            dp[i] = res;
            return res;
        }

        int res =  process2(str, i+1, dp);
        dp[i] = res;

        return res;
    }

    public static void main(String[] args) {
        String s = "226";
        System.out.println(numDecodings1(s));
        System.out.println(numDecodings2(s));
    }
}
