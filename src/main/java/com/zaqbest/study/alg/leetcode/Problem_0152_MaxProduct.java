package com.zaqbest.study.alg.leetcode;

/**
 * 152: 乘积最大子数组
 *
 * 算法描述：
 * https://leetcode-cn.com/problems/maximum-product-subarray/solution/hua-jie-suan-fa-152-cheng-ji-zui-da-zi-xu-lie-by-g/
 *
 * 如果碰到负数，交换最大和最小的数
 */
public class Problem_0152_MaxProduct {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;

        for (int n: nums){
            if (n < 0 ){
                int t = imax;
                imax = imin;
                imin = t;
            }

            imax = Math.max(imax * n, n);
            imin = Math.min(imin * n, n);
            max = Math.max(imax, max);
        }

        return max;
    }
}
