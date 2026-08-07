package com.zaqbest.study.alg.zcy.s30_great_offer.class52;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 避免洪水泛滥问题
 * 
 */
public class Problem_1488_AvoidFloodInTheCity {

	// rains[i] = j 第i天轮到j号湖泊下雨
	// 规定，下雨日，干啥 : -1
	// 不下雨日，如果没有湖泊可抽 : 1
	public static int[] avoidFlood(int[] rains) {
		int n = rains.length;
		int[] ans = new int[n];
		int[] invalid = new int[0];
		// key : 某个湖
		// value : 这个湖在哪些位置降雨
		// 4 : {3,7,19,21}
		// 1 : { 13 }
		// 2 : {4, 56}
		HashMap<Integer, LinkedList<Integer>> rainMap = new HashMap<>();
		for (int i = 0; i < n; i++) {
			if (rains[i] != 0) { // 第i天要下雨，rains[i]
				// 3天 9号
				// 9号 { 3 }
				// 9号 {1, 3}
				if (!rainMap.containsKey(rains[i])) {
					rainMap.put(rains[i], new LinkedList<>());
				}
				rainMap.get(rains[i]).addLast(i);
			}
		}
		// 没抽干的湖泊表
		// 某个湖如果满了，加入到set里
		// 某个湖被抽干了，从set中移除
		HashSet<Integer> set = new HashSet<>();
		// 这个堆的堆顶表示最先处理的湖是哪个
		PriorityQueue<Work> heap = new PriorityQueue<>();
		for (int i = 0; i < n; i++) { // 0 1 2 3 ...
			if (rains[i] != 0) {
				if (set.contains(rains[i])) {
					return invalid;
				}
				// 放入到没抽干的表里
				set.add(rains[i]);
				rainMap.get(rains[i]).pollFirst();
				if (!rainMap.get(rains[i]).isEmpty()) {
					heap.add(new Work(rains[i], rainMap.get(rains[i]).peekFirst()));
				}
				// 题目规定
				ans[i] = -1;
			} else { // 今天干活！
				if (heap.isEmpty()) {
					ans[i] = 1;
				} else {
					Work cur = heap.poll();
					set.remove(cur.lake);
					ans[i] = cur.lake;
				}
			}
		}
		return ans;
	}

	public static class Work implements Comparable<Work> {
		public int lake;
		public int nextRain;

		public Work(int l, int p) {
			lake = l;
			nextRain = p;
		}

		@Override
		public int compareTo(Work o) {
			return nextRain - o.nextRain;
		}
	}

}
