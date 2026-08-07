package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 字典序
 *
 * 思路
 * - 拓扑排序
 */
public class Problem_0269_AlienDictionary {

	public static String alienOrder(String[] words) {
		if (words == null || words.length == 0) {
			return "";
		}
		int N = words.length;
		HashMap<Character, Integer> indegree = new HashMap<>();
		for (int i = 0; i < N; i++) {
			for (char c : words[i].toCharArray()) {
				indegree.put(c, 0);
			}
		}
		HashMap<Character, HashSet<Character>> graph = new HashMap<>();
		for (int i = 0; i < N - 1; i++) {
			char[] cur = words[i].toCharArray();
			char[] nex = words[i + 1].toCharArray();
			//考虑最短的一个
			int len = Math.min(cur.length, nex.length);
			int j = 0;
			for (; j < len; j++) {
				//只要有一个不同，就不需要后面在比较了
				if (cur[j] != nex[j]) {
					if (!graph.containsKey(cur[j])) {
						graph.put(cur[j], new HashSet<>());
					}
					//新发现的字符，to字符入度增加1
					if (!graph.get(cur[j]).contains(nex[j])) {
						graph.get(cur[j]).add(nex[j]);
						indegree.put(nex[j], indegree.get(nex[j]) + 1);
					}
					break;
				}
			}
			//第一个比第二个长的情况，那么就不存在字典序
			if (j < cur.length && j == nex.length) {
				return "";
			}
		}
		StringBuilder ans = new StringBuilder();
		Queue<Character> q = new LinkedList<>();
		for (Character key : indegree.keySet()) {
			if (indegree.get(key) == 0) {
				q.offer(key);
			}
		}
		while (!q.isEmpty()) {
			char cur = q.poll();
			ans.append(cur);
			if (graph.containsKey(cur)) {
				for (char next : graph.get(cur)) {
					indegree.put(next, indegree.get(next) - 1);
					if (indegree.get(next) == 0) {
						q.offer(next);
					}
				}
			}
		}
		return ans.length() == indegree.size() ? ans.toString() : "";
	}

}
