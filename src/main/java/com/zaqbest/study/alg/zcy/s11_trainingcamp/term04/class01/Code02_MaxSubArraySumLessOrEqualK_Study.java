package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class01;

import java.util.TreeSet;

/**
 * 给定一个数组arr，再给定一个k值
 * 返回累加和小于等于k，但是离k最近的子数组累加和
 *
 * 思路： 前缀和，有序表
 */
public class Code02_MaxSubArraySumLessOrEqualK_Study {
    public static int getMaxLessOrEqualK(int[] arr, int K) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(0);
        int sum = 0;
        int ans = 0;

        for (int n: arr){
            sum = sum + n;
            if (treeSet.ceiling(sum-K) != null){
                ans = Math.max(ans, sum - treeSet.ceiling(sum -K));
            }
            treeSet.add(sum);
        }

        return ans;
    }
}
