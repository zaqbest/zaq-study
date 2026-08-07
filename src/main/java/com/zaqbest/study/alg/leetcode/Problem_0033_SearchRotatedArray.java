package com.zaqbest.study.alg.leetcode;

/**
 * 搜索旋转排序数组
 * 问题描述 https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 *
 * 问题算法
 */
public class Problem_0033_SearchRotatedArray {
    public int search(int[] nums, int target) {

        if (nums == null || nums.length == 0)
            return -1;

        int l = 0, r = nums.length - 1;
        while (l <= r){
            int m = (l + r) / 2 ;
            if (target == nums[m])
                return m;

            //左边是排序的情况
            if (nums[0] <= nums[m]){
                //注意处理边界情况
                if (target >= nums[0] && target < nums[m]){
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            //右边是排序的情况
            } else{
                if (target > nums[m] && target <= nums[r]){
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }
}
