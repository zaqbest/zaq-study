package com.zaqbest.study.alg.zcy.s30_great_offer.class26;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 126:单词接龙 II
 *
 * 本题测试链接 : https://leetcode.cn/problems/word-ladder-ii/
 *
 * 思路：
 * - 生成nexts表的过程：哈希表 或者 单词变形 => 根据数据量选择合适的策略
 * - bfs生成distance表
 * - dfs生成生成最短路径
 *
 * 	经典问题！！！！
 * 	递归设计能力
 */
public class Code04_WordLadderII {

	public static List<List<String>> findLadders(String start, String end, List<String> list) {
		list.add(start);
		HashMap<String, List<String>> nexts = getNexts(list);
		HashMap<String, Integer> distances = getDistances(start, nexts);
		LinkedList<String> pathList = new LinkedList<>();
		List<List<String>> res = new ArrayList<>();
		getShortestPaths(start, end, nexts, distances, pathList, res);
		return res;
	}

	//
	public static HashMap<String, List<String>> getNexts(List<String> words) {
		HashSet<String> dict = new HashSet<>(words);
		HashMap<String, List<String>> nexts = new HashMap<>();
		for (int i = 0; i < words.size(); i++) {
			nexts.put(words.get(i), getNext(words.get(i), dict));
		}
		return nexts;
	}

	// word, 在表中，有哪些邻居，把邻居们，生成list返回
	public static List<String> getNext(String word, HashSet<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		char[] chs = word.toCharArray();
		for (char cur = 'a'; cur <= 'z'; cur++) {
			for (int i = 0; i < chs.length; i++) {
				if (chs[i] != cur) {
					char tmp = chs[i];
					chs[i] = cur;
					if (dict.contains(String.valueOf(chs))) {
						res.add(String.valueOf(chs));
					}
					chs[i] = tmp;
				}
			}
		}
		return res;
	}

	// 生成距离表，从start开始，根据邻居表，宽度优先遍历，对于能够遇到的所有字符串，生成(字符串，距离)这条记录，放入距离表中
	public static HashMap<String, Integer> getDistances(String start, HashMap<String, List<String>> nexts) {
		HashMap<String, Integer> distances = new HashMap<>();
		distances.put(start, 0);
		Queue<String> queue = new LinkedList<>();
		queue.add(start);
		//不走重复路
		HashSet<String> set = new HashSet<>();
		set.add(start);
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			for (String next : nexts.get(cur)) {
				if (!set.contains(next)) {
					distances.put(next, distances.get(cur) + 1);
					queue.add(next);
					set.add(next);
				}
			}
		}
		return distances;
	}

	// cur 当前来到的字符串 可变
	// to 目标，固定参数
	// nexts 每一个字符串的邻居表
	// cur 到开头距离5 -> 到开头距离是6的支路 distances距离表
	// path : 来到cur之前，深度优先遍历之前的历史是什么
	// res : 当遇到cur，把历史，放入res，作为一个结果
	public static void getShortestPaths(String cur, String to, HashMap<String, List<String>> nexts,
			HashMap<String, Integer> distances, LinkedList<String> path, List<List<String>> res) {
		path.add(cur);
		if (to.equals(cur)) {
			res.add(new LinkedList<String>(path));
		} else {
			for (String next : nexts.get(cur)) {
				if (distances.get(next) == distances.get(cur) + 1) {
					getShortestPaths(next, to, nexts, distances, path, res);
				}
			}
		}
		path.pollLast();
	}

	public static void main(String[] args) {
		String start = "hit";
		String end = "cog";
		List<String> wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
		List<List<String>>  res= findLadders(start, end, wordList);
		System.out.println(JSONUtil.toJsonStr(res));
	}

}
