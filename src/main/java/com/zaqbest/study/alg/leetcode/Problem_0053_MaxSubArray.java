package com.zaqbest.study.alg.leetcode;

/**
 * 最大子序列和
 *
 * 问题链接
 * https://leetcode-cn.com/problems/maximum-subarray/
 *
 * 解决方案
 * https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
 */
public class Problem_0053_MaxSubArray {

    public static void main(String[] args) {
        int result = new Problem_0053_MaxSubArray().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(result);
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int prevAns = 0, maxAns = nums[0];
        for (int x: nums){
            prevAns = Math.max(x, prevAns + x);
            maxAns = Math.max(prevAns, maxAns);
        }

        return maxAns;
    }
}
