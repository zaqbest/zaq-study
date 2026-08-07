package com.zaqbest.study.alg.leetcode;

/**
 * 494 目标和
 *
 * 从左往右尝试模型
 * 记忆化搜索
 */
public class Problem_0494_FindTargetSumWays {
    public static int findTargetSumWays1(int[] nums, int target) {

        return helper(nums, nums.length-1, target);
    }

    /**
     * @param nums
     * @param index  只是用0..index，组成target有多少种方法
     * @param target
     * @return
     */
    public static int helper(int[] nums, int index, int target){
        if (index == 0){
            //如果是0，加正负号都可以
            if (target == 0){
                return nums[index] == target ? 2 : 0;
            }
            return nums[index] == target || nums[index] == -target ? 1 : 0;
        }

        return helper(nums, index-1, target-nums[index])
                + helper(nums, index-1, target+ nums[index]);
    }

    public static int findTargetSumWays2(int[] nums, int target) {
        int N = nums.length;

        int sum = Math.abs(target);

        for (int i = 0; i < N; i++){
            sum += nums[i];
        }

        int[][] dpPositive = new int[N][sum+2];
        int[][] dpNegative = new int[N][sum+2];

        for (int i = 0; i < N;i++){
            for (int s = 0; s <= sum;s++){
                dpPositive[i][s] = Integer.MIN_VALUE;
                dpNegative[i][s] = Integer.MIN_VALUE;
            }
        }
        return helper2(nums, nums.length-1, target, dpPositive, dpNegative);
    }

    /**
     * @param nums
     * @param index  只是用0..index，组成target有多少种方法
     * @param target
     * @return
     */
    public static int helper2(int[] nums, int index, int target, int[][] dpPositive, int[][] dpNegative){
        int[][] dp = target >= 0 ? dpPositive : dpNegative;
        if (dp[index][Math.abs(target)] > Integer.MIN_VALUE){
            return dp[index][Math.abs(target)];
        }

        if (index == 0){
            //如果是0，加正负号都可以
            if (target == 0){
                return nums[index] == target ? 2 : 0;
            }
            return nums[index] == target || nums[index] == -target ? 1 : 0;
        }

        int ans =  helper2(nums, index-1, target-nums[index],  dpPositive, dpNegative)
                + helper2(nums, index-1, target+ nums[index],  dpPositive, dpNegative);

        dp[index][Math.abs(target)] = ans;

        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,10};
        int target = 30;

        System.out.println(findTargetSumWays1(nums, target));
        System.out.println(findTargetSumWays2(nums, target));

        int[] nums2 = {1};
        int target2 = 1;
        System.out.println(findTargetSumWays1(nums2, target2));
        System.out.println(findTargetSumWays2(nums2, target2));

        int[] nums3 = {0,0,0,0,0,0,0,0,1};
        int target3 = 1;
        System.out.println(findTargetSumWays1(nums3, target3));
        System.out.println(findTargetSumWays2(nums3, target3));
    }
}
