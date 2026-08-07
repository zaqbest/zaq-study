package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

public class Problem_0078_Subsets_Study {

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ans  = new ArrayList<>();
		ans.add(new ArrayList<>());

		for (int i = 0; i < nums.length; i++){
			int size = ans.size();
			for (int j = 0; j < size; j++){
				List<Integer> item = new ArrayList<>(ans.get(j));
				item.add(nums[i]);
				ans.add(item);
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		List<List<Integer>> ans = subsets(new int[]{1,2,3,4});
		System.out.printf(JSONUtil.toJsonStr(ans));
	}
}
