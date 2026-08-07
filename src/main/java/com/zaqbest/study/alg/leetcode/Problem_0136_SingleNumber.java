package com.zaqbest.study.alg.leetcode;

/**
 * 136: 只出现一次的数字
 * 算法描述：
 * 异或  a^a = 0;  a ^ 0 = a
 */
public class Problem_0136_SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n: nums){
            res ^= n;
        }

        return res;
    }
}
