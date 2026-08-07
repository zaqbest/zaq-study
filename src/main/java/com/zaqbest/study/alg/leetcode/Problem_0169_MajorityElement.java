package com.zaqbest.study.alg.leetcode;

/**
 * 169：多数元素（超过n/2)
 *
 * 算法描述：https://leetcode-cn.com/problems/majority-element/solution/3chong-fang-fa-by-gfu-2/
 * 1，排序，则中间元素为超过n/2的元素
 * 2，摩尔投票法
 */
public class Problem_0169_MajorityElement {
    public int majorityElement(int[] nums) {
        int currNum = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++){
            if (currNum == nums[i]){
                count++;
            } else {
                if (count == 0){
                    currNum = nums[i];
                    count=1;
                } else{
                    count--;
                }
            }
        }

        return currNum;
    }
}
