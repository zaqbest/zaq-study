package com.zaqbest.study.alg.leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 215. 数组中的第K个最大元素
 *
 * 算法描述：
 * 优先队列
 */
public class Problem_0215_FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++){
            //不足K个时，直接放入队列
            if (i < k){
                q.offer(nums[i]);

            }
            //超过K个的元素，淘汰最小的
            else {
                int m = q.peek();
                if (nums[i] > m){
                    q.poll();
                    q.offer(nums[i]);
                }
            }
        }

        return q.poll();
    }
}
