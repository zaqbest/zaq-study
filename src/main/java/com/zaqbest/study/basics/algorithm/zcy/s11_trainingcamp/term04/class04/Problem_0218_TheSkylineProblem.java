package com.zaqbest.study.basics.algorithm.zcy.s11_trainingcamp.term04.class04;

import java.util.*;
import java.util.Map.Entry;

/**
 * 给定一个 N×3 的矩阵 matrix，对于每一个长度为 3 的小数组 arr，都表示一个大楼的三个数 据。arr[0]表示大楼的左边界，arr[1]表示大楼的右边界，arr[2]表示大楼的高度(一定大于 0)。 每座大楼的地基都在 X 轴上，大楼之间可能会有重叠，请返回整体的轮廓线数组。
 * 【举例】 matrix ={{2,5,6}, {1,7,4}, {4,6,7}, {3,6,5}, {10,13,2}, {9,11,3}, {12,14,4},{10,12,5} }
 * 返回: {{1,2,4},{2,4,6}, {4,6,7}, {6,7,4}, {9,10,3}, {10,12,5}, {12,14,4}}
 */
public class Problem_0218_TheSkylineProblem {

	public static class Node {
		public int x;
		public boolean isAdd;
		public int h;

		public Node(int x, boolean isAdd, int h) {
			this.x = x;
			this.isAdd = isAdd;
			this.h = h;
		}
	}

	public static class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			if (o1.x != o2.x) {
				return o1.x - o2.x;
			}
			if (o1.isAdd != o2.isAdd) {
				return o1.isAdd ? -1 : 1;
			}
			return 0;
		}
	}

	public static List<List<Integer>> getSkyline(int[][] matrix) {
		Node[] nodes = new Node[matrix.length * 2];
		for (int i = 0; i < matrix.length; i++) {
			nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]);
			nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]);
		}
		Arrays.sort(nodes, new NodeComparator());
		TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
		TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].isAdd) {
				if (!mapHeightTimes.containsKey(nodes[i].h)) {
					mapHeightTimes.put(nodes[i].h, 1);
				} else {
					mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) + 1);
				}
			} else {
				if (mapHeightTimes.get(nodes[i].h) == 1) {
					mapHeightTimes.remove(nodes[i].h);
				} else {
					mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) - 1);
				}
			}
			if (mapHeightTimes.isEmpty()) {
				mapXHeight.put(nodes[i].x, 0);
			} else {
				mapXHeight.put(nodes[i].x, mapHeightTimes.lastKey());
			}
		}
		List<List<Integer>> ans = new ArrayList<>();
		for (Entry<Integer, Integer> entry : mapXHeight.entrySet()) {
			int curX = entry.getKey();
			int curMaxHeight = entry.getValue();
			if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight) {
				ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
			}
		}
		return ans;
	}

}
