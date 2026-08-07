package com.zaqbest.study.alg.leetcode;

/**
 * 287.寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 * 数组转换为链表，求链表的循环点
 *
 * 算法描述
 * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/287xun-zhao-zhong-fu-shu-by-kirsche/
 */
public class Problem_0278_FindDuplicate {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        slow = nums[slow];
        fast = nums[nums[fast]];

        while (slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        int pre1 = 0;
        int pre2 = slow;
        while (pre1 != pre2){
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }

        return pre1;
    }
}
