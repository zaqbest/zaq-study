package com.zaqbest.study.alg.leetcode;

/**
 * 238:除自身外数组的乘积
 * 限制：不能用除法
 *
 * 问题描述：https://leetcode-cn.com/problems/product-of-array-except-self/
 *
 * 算法描述
 * 上三角，下三角
 * https://leetcode-cn.com/problems/product-of-array-except-self/solution/product-of-array-except-self-shang-san-jiao-xia-sa/
 *
 */
public class Problem_0238_ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int p = 1, q =1;

        //计算下三角
        for (int i = 0; i < nums.length; i++){
            res[i] = p;
            p *= nums[i];
        }

        //计算上三角
        for(int i = nums.length; i > 0; i--){
            res[i-1] *= q;
            q *= nums[i-1];
        }

        return res;
    }
}
