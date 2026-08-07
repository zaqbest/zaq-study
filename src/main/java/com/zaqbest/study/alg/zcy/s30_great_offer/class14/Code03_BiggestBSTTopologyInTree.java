package com.zaqbest.study.alg.zcy.s30_great_offer.class14;

/**
 * 了解思路就行了  可太难了
 *
 *  本题测试链接 : https://www.nowcoder.com/practice/e13bceaca5b14860b83cbcc4912c5d4a
 *  提交以下的代码，并把主类名改成Main
 *  可以直接通过
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Code03_BiggestBSTTopologyInTree {

	// h: 代表当前的头节点
	// t: 代表树 t[i][0]是i节点的父节点、t[i][1]是i节点的左孩子、t[i][2]是i节点的右孩子
	// m: i节点为头的最大bst拓扑结构大小 -> m[i]
	// 返回: 以h为头的整棵树上，最大bst拓扑结构的大小
	public static int maxBSTTopology(int h, int[][] t, int[] m) {
		if (h == 0) {
			return 0;
		}
		int l = t[h][1];
		int r = t[h][2];
		int c = 0;
		int p1 = maxBSTTopology(l, t, m);
		int p2 = maxBSTTopology(r, t, m);
		while (l < h && m[l] != 0) {
			l = t[l][2];
		}
		if (m[l] != 0) {
			c = m[l];
			while (l != h) {
				m[l] -= c;
				l = t[l][0];
			}
		}
		while (r > h && m[r] != 0) {
			r = t[r][1];
		}
		if (m[r] != 0) {
			c = m[r];
			while (r != h) {
				m[r] -= c;
				r = t[r][0];
			}
		}
		m[h] = m[t[h][1]] + m[t[h][2]] + 1;
		return Math.max(Math.max(p1, p2), m[h]);
	}

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node (int data){
			this.value = data;
		}
	}

	// 看不懂，头疼！！！
	public static int bstTopoSize1(Node head){
		if (head == null){
			return 0;
		}
		int max = maxTopo(head, head);
		max = Math.max(bstTopoSize1(head.left), max);
		max = Math.max(bstTopoSize1(head.right), max);

		return max;
	}

	/**
	 *
	 * @param h  树的根节点
	 * @param n  当前节点
	 * @return
	 */
	public static int maxTopo(Node h, Node n){
		if (h != null && n != null && isBstNode(h, n, n.value)){
			int ans = maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
			//System.out.println(StrUtil.format("maxTopo:node={},  ans = {}",n.value, ans));
			return ans;
		}
		//System.out.println(StrUtil.format("maxTopo:node={},  ans = {}",n == null ? null : n.value, 0));
		return 0;
	}

	public static boolean isBstNode(Node h , Node n, int value){
		boolean ans;
		if (h == null){
			ans =  false;
		} else if (h == n){
			ans =  true;
		} else {
			ans = isBstNode(h.value > value ? h.left: h.right, n, value);
		}
		//System.out.println(StrUtil.format("isBstNode: node={}, ans={}", n.value, ans));
		return ans;
	}

	//for test
	public static Node deserialize(String data) {
		String[] strs = data.substring(1, data.length() - 1).split(",");
		int index = 0;
		Node root = generateNode(strs[index++]);
		Queue<Node> queue = new LinkedList<Node>();
		if (root != null) {
			queue.add(root);
		}
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			node.left = generateNode(index == strs.length ? "null" : strs[index++]);
			node.right = generateNode(index == strs.length ? "null" : strs[index++]);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return root;
	}
	//for test
	private static Node generateNode(String val) {
		if (val.equals("null")) {
			return null;
		}
		return new Node(Integer.valueOf(val));
	}
	public static void main(String[] args) {
		Node root = deserialize("[10,5,15,2,6,12,16,null,null,null,11,13]");
		int max = bstTopoSize1(root);
		System.out.println(max);
		//System.out.println(root);
	}
	
	public static void testAcm(){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int h = sc.nextInt();
		int[][] tree = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			int c = sc.nextInt();
			int l = sc.nextInt();
			int r = sc.nextInt();
			tree[l][0] = c;
			tree[r][0] = c;
			tree[c][1] = l;
			tree[c][2] = r;
		}
		System.out.println(maxBSTTopology(h, tree, new int[n + 1]));
		sc.close();
	}

}
