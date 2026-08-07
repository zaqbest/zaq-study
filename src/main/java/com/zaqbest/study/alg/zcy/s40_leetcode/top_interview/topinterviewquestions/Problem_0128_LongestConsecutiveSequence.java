package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

import java.util.HashMap;

/**
 * 最长连续序列
 */
public class Problem_0128_LongestConsecutiveSequence {

	public static int longestConsecutive(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int len = 0;
		for (int num : nums) {
			if (!map.containsKey(num)) {
				map.put(num, 1);
				//以num-1结尾有多少个
				int preLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
				//以num+1开头有多少个
				int posLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
				int all = preLen + posLen + 1;
				map.put(num - preLen, all);
				map.put(num + posLen, all);
				len = Math.max(len, all);
			}
		}
		return len;
	}

}
