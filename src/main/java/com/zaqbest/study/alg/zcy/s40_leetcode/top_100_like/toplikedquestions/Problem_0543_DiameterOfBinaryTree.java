package com.zaqbest.study.alg.zcy.s40_leetcode.top_100_like.toplikedquestions;

/**
 * 二叉树的最大直径长度
 *
 * 思路
 * - 二叉树的递归套路
 *
 * 可能性
 * x为头结点
 * 	1，与x有关，x.left的高度+x.right的高度+1
 * 	2，与x无关，max(x.left的直径, x.right的直径)
 */
public class Problem_0543_DiameterOfBinaryTree {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static int diameterOfBinaryTree(TreeNode root) {
		return process(root).maxDistance;
	}

	public static class Info {
		public int maxDistance;
		public int height;

		public Info(int m, int h) {
			maxDistance = m;
			height = h;
		}
	}

	public static Info process(TreeNode x) {
		if (x == null) {
			return new Info(0, 0);
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
				leftInfo.height + rightInfo.height);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		return new Info(maxDistance, height);
	}

}
