package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.utils.ArrayUtil;

import java.util.Arrays;

/**
 * 下一个排列
 *
 * https://leetcode-cn.com/problems/next-permutation/
 *
 * 维基百科解法
 * https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-powcai/
 */
public class Problem_0031_NextPermutation {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,7,4,3,1};
        new Problem_0031_NextPermutation().nextPermutation(nums);

        ArrayUtil.printArray(nums);
    }

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 1)
            return;
        //查找最大的k,满足num[k]<num[k+1]
        int k = -1;
        for (int i = 0; i < nums.length - 1; i++){
            if (nums[i] < nums[i+1]){
                k = i;
            }
        }

        //已经是最大了，直接翻转
        if (k == -1){
            reverse(nums);
            return;
        }

        //查找最大的l,满足num[l] > num[k]
        int l = -1;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > nums[k]){
                l = i;
            }
        }

        //交换num[k]和num[l]
        swap(nums, k, l);

        //翻转num[k+1:]
        int[] temp = reverse(Arrays.copyOfRange(nums,k+1, nums.length));
        System.arraycopy(temp,0, nums, k+1, temp.length);

    }

    private int[] reverse(int[] nums) {
        for (int k = 0; k < nums.length/2; k++) {
            int temp = nums[k];
            nums[k] = nums[nums.length - (1 + k)];
            nums[nums.length - (1 + k)] = temp;
        }

        return nums;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
