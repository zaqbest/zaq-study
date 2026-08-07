package com.zaqbest.study.alg.zcy.s10_primary.class08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 最低公共祖先
 */
public class Code07_lowestAncestor {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 基于map实现，存储节点对应的父节点
	 *
	 * @param head
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static Node lowestAncestor1(Node head, Node o1, Node o2) {
		if (head == null) {
			return null;
		}
		HashMap<Node, Node> parentMap = new HashMap<>();
		parentMap.put(head, null);
		fillParentMap(head, parentMap);
		HashSet<Node> o1Set = new HashSet<>();
		Node cur = o1;
		o1Set.add(cur);
		while (parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			o1Set.add(cur);
		}
		cur = o2;
		while (!o1Set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		return cur;
	}

	public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
		if (head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
	}

	/**
	 * 递归算法
	 * 对于X为根节点的树
	 * 1，O1,O2都不在X上
	 * 2，只有一个在X上
	 * 3，O1,O2都在X为头的树上
	 * 	  3.1 左右各一个
	 *    3.2 都在左树上
	 *    3.3 都在右树上
	 *    3.4 X是O1或者O2
	 *
	 * @param head
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static Node lowestAncestor2(Node head, Node o1, Node o2) {
		return process(head, o1, o2).ans;
	}

	//任何子树
	public static class Info {
		public Node ans;//最初交汇点
		public boolean findO1;//是否发现O1
		public boolean findO2;

		public Info(Node a, boolean f1, boolean f2) {
			ans = a;
			findO1 = f1;
			findO2 = f2;
		}
	}

	public static Info process(Node X, Node o1, Node o2) {
		if (X == null) {
			return new Info(null, false, false);
		}
		Info leftInfo = process(X.left, o1, o2);
		Info rightInfo = process(X.right, o1, o2);

		boolean findO1 = X == o1 || leftInfo.findO1 || rightInfo.findO1;
		boolean findO2 = X == o2 || leftInfo.findO2 || rightInfo.findO2;
		// O1,O2最初的交汇点在哪里
		// 1) 左树提前交汇
		// 2) 右树提前交汇
		// 3) 在X点交汇
		// 4) 没有交汇
		Node ans = null;
		if (leftInfo.ans != null) {
			ans = leftInfo.ans;
		}
		if (rightInfo.ans != null) {
			ans = rightInfo.ans;
		}
		if (ans == null) {
			if (findO1 && findO2) {
				ans = X;
			}
		}
		return new Info(ans, findO1, findO2);
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	// for test
	public static Node pickRandomOne(Node head) {
		if (head == null) {
			return null;
		}
		ArrayList<Node> arr = new ArrayList<>();
		fillPrelist(head, arr);
		int randomIndex = (int) (Math.random() * arr.size());
		return arr.get(randomIndex);
	}

	// for test
	public static void fillPrelist(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Node o1 = pickRandomOne(head);
			Node o2 = pickRandomOne(head);
			if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
