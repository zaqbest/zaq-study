package com.zaqbest.study.alg.zcy.s10_primary.class08;

import java.util.LinkedList;

/**
 * 是否完全二叉树
 */
public class Code06_IsCBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 算法描述
	 * 1，按照层序遍历
	 * 2，如果碰到过一个两个孩子不全的节点
	 * 	  那么之后碰到的节点都必须是叶子节点
	 * @param head
	 * @return
	 */
	public static boolean isCBT1(Node head) {
		if (head == null) {
			return true;
		}
		LinkedList<Node> queue = new LinkedList<>();
		// 是否遇到过左右两个孩子不双全的节点
		boolean leaf = false;
		Node l = null;
		Node r = null;
		queue.add(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			if (
			// 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
			(leaf && !(l == null && r == null))
					//左节点为空，而右节点不为空的情况
					|| (l == null && r != null)
			) {
				return false;
			}
			if (l != null) {
				queue.add(l);
			}
			if (r != null) {
				queue.add(r);
			}
			if (l == null || r == null) {
				leaf = true;
			}
		}
		return true;
	}

	//二叉数递归
	public static boolean isCBT2(Node head) {
		if (head == null) {
			return true;
		}
		return process(head).isCBT;
	}

	public static class Info {
		public boolean isFull;//是否是满二叉树
		public boolean isCBT;//是否是完全二叉树
		public int height;//高度

		public Info(boolean full, boolean cbt, int h) {
			isFull = full;
			isCBT = cbt;
			height = h;
		}
	}

	/**
	 * 递归处理
	 * 1，满二叉树，高度相同
	 * 2，左侧是完全二叉树，右侧是满二叉树，左侧高度=右侧高度+1
	 * 3,左侧是满二叉树，右侧是满二叉树，左侧高度=右侧高度+1
	 * 4，左侧是满二叉树，右侧是完全二叉树，左侧高度=右侧高度
	 * @param head
	 * @return
	 */
	public static Info process(Node head) {
		if (head == null) {
			return new Info(true, true, 0);
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		boolean isCBT = false;
		if (isFull) {
			isCBT = true;
		} else {
			//两边必须是完全二叉树
			if (leftInfo.isCBT && rightInfo.isCBT) {
				//条件2
				if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				//条件3
				if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				//条件4
				if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
					isCBT = true;
				}
			}
		}
		return new Info(isFull, isCBT, height);
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

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isCBT1(head) != isCBT2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
