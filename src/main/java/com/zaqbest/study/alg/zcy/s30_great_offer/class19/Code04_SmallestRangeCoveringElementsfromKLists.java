package com.zaqbest.study.alg.zcy.s30_great_offer.class19;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * 最小区间
 *
 * 本题测试链接 : https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/
 *
 * 思路
 * - 有序表（TreeSet), 最小值向左滑动
 */
public class Code04_SmallestRangeCoveringElementsfromKLists {

	public static class Node {
		public int value; //数值
		public int arrid; //数组的id
		public int index; //数组内的index

		public Node(int v, int ai, int i) {
			value = v;
			arrid = ai;
			index = i;
		}
	}

	public static class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.value != o2.value ? o1.value - o2.value : o1.arrid - o2.arrid;
		}

	}

	public static int[] smallestRange(List<List<Integer>> nums) {
		int N = nums.size();
		TreeSet<Node> orderSet = new TreeSet<>(new NodeComparator());
		for (int i = 0; i < N; i++) {
			orderSet.add(new Node(nums.get(i).get(0), i, 0));
		}
		boolean set = false;
		int a = 0;
		int b = 0;
		while (orderSet.size() == N) {
			Node min = orderSet.first();
			Node max = orderSet.last();
			if (!set || (max.value - min.value < b - a)) {
				set = true;
				a = min.value;
				b = max.value;
			}
			min = orderSet.pollFirst();
			int arrid = min.arrid;
			int index = min.index + 1;
			if (index != nums.get(arrid).size()) {
				orderSet.add(new Node(nums.get(arrid).get(index), arrid, index));
			}
		}
		return new int[] { a, b };
	}

	public static void main(String[] args) {
		List<List<Integer>> nums = new ArrayList();
		nums.add(new ArrayList<>(Arrays.asList(4,10,15,24,26)));
		nums.add(new ArrayList<>(Arrays.asList(0,9,12,20)));
		nums.add(new ArrayList<>(Arrays.asList(5,18,22,30)));

		int[] res = smallestRange(nums);
		System.out.println(JSONUtil.toJsonStr(res));
	}
}
