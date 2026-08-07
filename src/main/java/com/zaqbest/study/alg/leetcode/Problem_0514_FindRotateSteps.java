package com.zaqbest.study.alg.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 514 自由之路
 *
 * 关键词：
 * 范围尝试模型
 * 记忆化搜索
 */
public class Problem_0514_FindRotateSteps {
    public static int findRotateSteps(String r, String k) {
        char[] ring = r.toCharArray();
        int rsize = ring.length;
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < rsize; i++) {
            if (!map.containsKey(ring[i])) {
                map.put(ring[i], new ArrayList<>());
            }
            map.get(ring[i]).add(i);
        }
        int[][] dp = new int[rsize][k.length() + 1];
        for (int i = 0; i < rsize; i++) {
            for (int j = 0; j <= k.length(); j++) {
                dp[i][j] = -1;
            }
        }
        return minSteps2(0, 0, k.toCharArray(), map, rsize, dp);
    }

    public static int minSteps2(int preStrIndex, int keyIndex, char[] key, HashMap<Character, ArrayList<Integer>> map,
                                int rsize, int[][] dp) {
        if (dp[preStrIndex][keyIndex] != -1) {
            return dp[preStrIndex][keyIndex];
        }
        if (keyIndex == key.length) {
            dp[preStrIndex][keyIndex] = 0;
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int curStrIndex : map.get(key[keyIndex])) {
            int step = dial(preStrIndex, curStrIndex, rsize) + 1
                    + minSteps2(curStrIndex, keyIndex + 1, key, map, rsize, dp);
            ans = Math.min(ans, step);
        }
        dp[preStrIndex][keyIndex] = ans;
        return ans;
    }

    public static int dial(int i1, int i2, int size) {
        return Math.min(Math.abs(i1 - i2), Math.min(i1, i2) + size - Math.max(i1, i2));
    }
}
