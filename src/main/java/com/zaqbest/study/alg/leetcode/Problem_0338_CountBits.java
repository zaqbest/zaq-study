package com.zaqbest.study.alg.leetcode;

/**
 * 338. 比特位计数
 *
 */
public class Problem_0338_CountBits {
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        res[0] = 0;

        for (int i = 1; i <= n; i++){
            //奇数
            if (i % 2 == 1){
                res[i] = res[(i-1)/2] +1 ;
            } else {
                res[i] = res[(i)/2] ;
            }
        }

        return res;
    }
}
