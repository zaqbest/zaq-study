package com.zaqbest.study.alg.leetcode;

import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * 问题描述
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class Problem_0034_SearchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};

        //使用二分查找目标数
        int res = Arrays.binarySearch(nums, target);
        if (res < 0){
            return new int[]{-1, -1};
        } else{
            int l = res, r = res;
            while (l >=0 && nums[l] == target){
                l--;
            }
            while (r <= nums.length-1 && nums[r] == target){
                r++;
            }

            return new int[]{l+1, r-1};
        }
    }
}
