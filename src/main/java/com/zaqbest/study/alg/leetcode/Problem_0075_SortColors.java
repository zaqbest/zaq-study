package com.zaqbest.study.alg.leetcode;

/**
 * 75：颜色分类
 */
public class Problem_0075_SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        int len = nums.length;
        int s1=0, s2=0, s0= 0;

        for (int i = 0; i < len; i++){
            if (nums[i] == 0){
                s0++;
            } else if (nums[i] == 1){
                s1++;
            } else {
                s2++;
            }
        }

        int j = 0;
        for (int i = 0; i < s0; i++) nums[j++] = 0;
        for (int i = 0; i < s1; i++) nums[j++] = 1;
        for (int i = 0; i < s2; i++) nums[j++] = 2;
    }
}
