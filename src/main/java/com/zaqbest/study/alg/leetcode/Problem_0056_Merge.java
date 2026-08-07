package com.zaqbest.study.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 */
public class Problem_0056_Merge {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<int[]> merged = new ArrayList<>();

        int max, i, j = 0;
        while ((i = j) < intervals.length){
            max = intervals[i][1];
            j = i;

            //对于重合的情况，获取右边界的最大值
            while (j < intervals.length && intervals[j][0] <= max){
                max = Math.max(intervals[j++][1], max);// j++向右增长
            }

            merged.add(new int[]{intervals[i][0], max});
        }

        return merged.toArray(new int[merged.size()][2]);
    }
}
