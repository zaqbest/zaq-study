package com.zaqbest.study.alg.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 0026. 删除有序数组中的重复项
 */
public class Problem_0026_RemoveDuplicates {

    public static int removeDuplicates1(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int n: nums){
            set.add(n);
        }

        return set.size();
    }

    public static int removeDuplicates2(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        if (nums.length == 1){
            return 1;
        }

        int index =1;//下一个被写入的位置
        int prev = nums[0];//上一个值
        for (int i = 1; i < nums.length; i++){
            int cur = nums[i];
            if (cur == prev){

            } else {
                nums[index++] = cur;
            }

            prev = cur;
        }

        return index;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,4,4,5};
        System.out.println(removeDuplicates1(nums));
        System.out.println(removeDuplicates2(nums));
    }
}
