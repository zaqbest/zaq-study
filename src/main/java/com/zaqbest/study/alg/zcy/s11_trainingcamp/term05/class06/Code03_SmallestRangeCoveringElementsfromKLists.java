package com.zaqbest.study.alg.zcy.s11_trainingcamp.term05.class06;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * 你有k个 非递减排列 的整数列表。找到一个 最小 区间，使得k个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果b-a < d-c或者在b-a == d-c时a < c，则区间 [a,b] 比 [c,d] 小。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 * 示例 2：
 *
 * 输入：nums = [[1,2,3],[1,2,3],[1,2,3]]
 * 输出：[1,1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code03_SmallestRangeCoveringElementsfromKLists {

	public static class Node {
		public int value;
		public int arrid;
		public int index;

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

}
