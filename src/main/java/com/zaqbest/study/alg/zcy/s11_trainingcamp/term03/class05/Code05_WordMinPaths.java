package com.zaqbest.study.alg.zcy.s11_trainingcamp.term03.class05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 给定两个字符串，记为start和to，再给定一个字符串列表list，list中一定包含to list中没有重复字符串，所有的字符串都是小写的。
 * 规定: start每次只能改变一个字符，最终的目标是彻底变成to，但是每次变成的新字符串必须在list 中存在。
 * 请返回所有最短的变换路径。
 * 【举例】
 * start="abc",end="cab",list={"cab","acc","cbc","ccc","cac","cbb","aab","abb"}
 * 转换路径的方法有很多种，但所有最短的转换路径如下:
 * abc -> abb -> aab -> cab
 * abc -> abb -> cbb -> cab
 * abc -> cbc -> cac -> cab
 * abc -> cbc -> cbb -> cab
 */
public class Code05_WordMinPaths {

	public static List<List<String>> findMinPaths(String start, String end,
			List<String> list) {
		list.add(start);
		//生成邻居表 K-字符串，V-通过变换一个位置，可以得到的字符串
		HashMap<String, ArrayList<String>> nexts = getNexts(list);
		//求所有的字符串到start的最短距离是多少，宽度优先遍历（BFS）
		HashMap<String, Integer> distances = getDistances(start, nexts);
		LinkedList<String> pathList = new LinkedList<>();
		List<List<String>> res = new ArrayList<>();
		//深度优先遍历（DFS）
		getShortestPaths(start, end, nexts, distances, pathList, res);
		return res;
	}

	public static HashMap<String, ArrayList<String>> getNexts(List<String> words) {
		Set<String> dict = new HashSet<>(words); // List 所有东西放入 set
		HashMap<String, ArrayList<String>> nexts = new HashMap<>();
		for (int i = 0; i < words.size(); i++) {
			nexts.put(words.get(i), getNext(words.get(i), dict));
		}
		return nexts;
	}

	private static ArrayList<String> getNext(String word, Set<String> dict) {
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

	//宽度优先遍历（BFS）
	public static HashMap<String, Integer> getDistances(String start,
			HashMap<String, ArrayList<String>> nexts) {
		HashMap<String, Integer> distances = new HashMap<>();
		distances.put(start, 0);
		Queue<String> queue = new LinkedList<String>();
		queue.add(start);
		//是否已经处理过
		HashSet<String> seen = new HashSet<String>();
		seen.add(start);
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			for (String next : nexts.get(cur)) {
				if (!seen.contains(next)) {
					distances.put(next, distances.get(cur) + 1);
					queue.add(next);
					seen.add(next);
				}
			}
		}
		return distances;
	}

	private static void getShortestPaths(String cur, String end,
			HashMap<String, ArrayList<String>> nexts,
			HashMap<String, Integer> distances, LinkedList<String> path,
			List<List<String>> res) {
		path.add(cur);
		if (end.equals(cur)) {
			res.add(new LinkedList<>(path));
		} else {
			for (String next : nexts.get(cur)) {
				//往更远的路径走，不要走回头路
				if (distances.get(next) == distances.get(cur) + 1) {
					getShortestPaths(next, end, nexts, distances, path, res);
				}
			}
		}
		//撤销path.add(cur)的现场，开始尝试其他的路径
		path.pollLast();
	}

	public static void main(String[] args) {
		String start = "abc";
		String end = "cab";
		String[] test = { "abc", "cab", "acc", "cbc", "ccc", "cac", "cbb",
				"aab", "abb" };
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < test.length; i++) {
			list.add(test[i]);
		}
		List<List<String>> res = findMinPaths(start, end, list);
		for (List<String> obj : res) {
			for (String str : obj) {
				System.out.print(str + " -> ");
			}
			System.out.println();
		}

	}

}
