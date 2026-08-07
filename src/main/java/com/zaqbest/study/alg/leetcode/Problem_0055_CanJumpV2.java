package com.zaqbest.study.alg.leetcode;

/**
 * leetcode55: 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 *
 * 大神算法
 * https://leetcode-cn.com/problems/jump-game/solution/55-by-ikaruga/
 *
 */
public class Problem_0055_CanJumpV2 {
    public boolean canJump(int[] nums) {
        if(nums == null)
            return false;

        int max = 0; //当前能跳到的最远距离
        for(int i = 0; i <= max; i++){
            max = Math.max(max, i + nums[i]);
            //已超过最大长度
            if (max >= nums.length - 1)
                return true;
        }
        return false;
    }
}
