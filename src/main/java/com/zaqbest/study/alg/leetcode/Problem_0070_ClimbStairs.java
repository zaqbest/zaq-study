package com.zaqbest.study.alg.leetcode;

/**
 * leetcode70: 爬楼梯问题
 */
public class Problem_0070_ClimbStairs {
    /**
     * f（n） = f(n-1)+f(n-2),n>=3
     * f(1)=1, f(2) =2
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int fn1 = 2; //f(n-1)
        int fn2 = 1; //f(n-2)
        int t = 0;
        for (int i = 3; i <= n; i++){
            t = fn1 + fn2;
            fn2 = fn1;
            fn1 = t;
        }

        return t;
    }
}
