package com.zaqbest.study.alg.leetcode;

/**
 * 283. 移动零
 * https://leetcode-cn.com/problems/move-zeroes/
 */
public class Problem_0283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        int n = nums.length;
        int dst = 0;
        for (int i = 0; i < n; i++){
            if (nums[i] != 0){
                nums[dst++] = nums[i];
            }
        }
        while (dst < n){
            nums[dst++] = 0;
        }
    }
}
