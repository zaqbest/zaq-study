package com.zaqbest.study.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和
 *
 * 问题描述
 * https://leetcode-cn.com/problems/combination-sum/
 *
 * 解决算法
 * https://leetcode-cn.com/problems/combination-sum/solution/
 */
public class Problem_0039_CombinationSum {

    public static void main(String[] args) {
        int[] candidates = new int[]{2,3,6,7,1,4};
        int target = 11;
        List<List<Integer>> res = new Problem_0039_CombinationSum().combinationSum(candidates, target);
        for (List l: res){
            System.out.println(l);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);

        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            //恢复现场
            combine.remove(combine.size() - 1);
        }
    }
}
