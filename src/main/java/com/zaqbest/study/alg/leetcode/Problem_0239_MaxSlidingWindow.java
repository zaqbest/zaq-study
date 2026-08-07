package com.zaqbest.study.alg.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 239 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * 算法描述
 * 优先队列
 * https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
 */
public class Problem_0239_MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //value, index, 按照值从大到小排序，值相同的情况下，安装index从大到小排序
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0]: pair2[1]-pair1[0];
            }
        });

        int n = nums.length;
        for (int i =0; i < k; i++){
            pq.offer(new int[]{nums[i], i});
        }

        int[] ans = new int[n-k+1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; i++){
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k){
                pq.poll();
            }
            ans[i-k+1] = pq.peek()[0];
        }
        return ans;
    }
}
