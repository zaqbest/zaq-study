package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.utils.ArrayUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为 K 的子数组
 */
public class Problem_0560_SubarraySum {
    public static int f1(int[] nums, int k){
        int N = nums.length;

        int[] sums = new int[N];
        sums[0] = nums[0];
        for (int i = 1; i < N; i++){
            sums[i] = sums[i-1] + nums[i];
        }

        int ans = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j <= i;j++){
                if (sums[i] - (j > 0 ? sums[j-1] : 0) == k){
                    ans++;
                }
            }
        }

        return ans;
    }

    public static int subarraySum(int[] nums, int k) {
        int N = nums.length;

        int[] sums = new int[N];
        sums[0] = nums[0];
        for (int i = 1; i < N; i++){
            sums[i] = sums[i-1] + nums[i];
        }

        int ans = 0;
        int sum = 0;
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 1);
        for (int i = 0; i < N;i++){
            if (sumMap.containsKey(sums[i] - k)){
                ans += sumMap.get(sums[i] - k);
            }

            sum+= nums[i];

            if (sumMap.containsKey(sum)){
                sumMap.put(sum, sumMap.get(sum)+1);
            } else {
                sumMap.put(sum, 1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++){
            int k = 100;
            int[] nums = ArrayUtil.generateRandomArray(100, 1000);

            if (nums.length ==0)
                continue;

            int r1 = f1(nums, k);
            int r2 = subarraySum(nums, k);

            if (r1 != r2){
                System.out.println("oops!");
//                System.out.println(JSONUtil.toJsonStr(nums));
                System.out.println(r1);
                System.out.println(r2);
                System.out.println(i);
                break;
            }
        }
        System.out.println("finish!");

    }
}
