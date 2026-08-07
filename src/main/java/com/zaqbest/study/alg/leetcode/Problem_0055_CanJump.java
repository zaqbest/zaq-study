package com.zaqbest.study.alg.leetcode;

/**
 * leetcode55: 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 *
 * 处理速度慢，大数据会超时
 *
 */
public class Problem_0055_CanJump {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0)
            return false;

        return canJump(nums, 0, nums.length-1);
    }

    /**
     * 判断从from位置能否到达to位置
     *
     * @param nums
     * @param from
     * @param to
     * @return
     */
    private boolean canJump(int[] nums, int from, int to){
        if (from == to){
            return true;
        }
        for (int i = nums[from]; i >=1; i--){
            if (from + i >= to) return true;
            //可以有1..nums[from]种选择，只要任意一种可以到达就可以算成功
            if (canJump(nums, from+i, to)){
                return true;
            }
        }
        return false;
    }
}
