package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 跳格子升级问题
 *
 * 给定一个数组arr，arr[i]表示每次必须跳arr[i]个格子
 * 开始位置s，结束位置e,
 * 问至少需要多少步，可以从s跳到e,如果无法调到返回-1
 *
 * 思路
 * 宽度优先遍历bfs
 */
public class Problem_0045_JumpGameIIFollowUp {
    /**
     *
     * @param arr
     * @param N
     * @param start 0..N-1
     * @param end 0..N-1
     * @return
     */
    public int jumpMinStep(int[] arr, int N, int start, int end){
        if (start < 0 || start >= N || end < 0 || end >= N){
            return -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        HashMap<Integer, Integer> levelMap = new HashMap<>();
        queue.add(start);
        levelMap.put(start, 0);

        while (!queue.isEmpty()){
            int cur = queue.poll();
            int level = levelMap.get(cur);

            if (cur == end){
                return level;
            } else {
                int left = cur - arr[cur];
                int right = cur + arr[cur];

                if (left >=0 && !levelMap.containsKey(left)){
                    queue.add(left);
                    levelMap.put(left, level + 1);
                }

                if (right <= N && !levelMap.containsKey(right)){
                    queue.add(right);
                    levelMap.put(right, level + 1);
                }
            }
        }

        return -1;
    }


}
