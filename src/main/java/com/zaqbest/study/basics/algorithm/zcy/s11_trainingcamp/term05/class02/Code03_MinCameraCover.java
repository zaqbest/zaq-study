package com.zaqbest.study.basics.algorithm.zcy.s11_trainingcamp.term05.class02;

/**
 * 给定一棵二叉树的头节点head，如果在某一个节点x上放置相机，
 * 那么x的父节点、x的所有子节点以及x都可以被覆盖。
 * 返回如果要把所有数都覆盖，至少需要多少个相机。
 */
public class Code03_MinCameraCover {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
	}

	public static int minCameraCover1(Node root) {
		Info data = process1(root);
		return (int) Math.min(data.uncovered + 1,
				Math.min(data.coveredNoCamera, data.coveredHasCamera));
	}

	// 潜台词：x是头节点，x下方的点都被覆盖的情况下
	public static class Info {
		public long uncovered; // x没有被覆盖，x为头的树至少需要几个相机
		public long coveredNoCamera; // x被相机覆盖，但是x没相机，x为头的树至少需要几个相机
		public long coveredHasCamera; // x被相机覆盖了，并且x上放了相机，x为头的树至少需要几个相机

		public Info(long un, long no, long has) {
			uncovered = un;
			coveredNoCamera = no;
			coveredHasCamera = has;
		}
	}

	// 所有可能性都穷尽了
	public static Info process1(Node X) {	
		if (X == null) { // base case
			return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
		}
		
		
		Info left = process1(X.left);
		Info right = process1(X.right);
		// x  uncovered  x自己不被覆盖，x下方所有节点，都被覆盖
		//  左孩子： 左孩子没被覆盖，左孩子以下的点都被覆盖
		//          左孩子被覆盖但没相机，左孩子以下的点都被覆盖
		//          左孩子被覆盖也有相机，左孩子以下的点都被覆盖
		long uncovered = left.coveredNoCamera + right.coveredNoCamera;
		
		
		
		// x下方的点都被covered，x也被cover，但x上没相机
		long coveredNoCamera = Math.min(
				// 1)
				left.coveredHasCamera + right.coveredHasCamera,
				
				Math.min(
						// 2) 
						left.coveredHasCamera + right.coveredNoCamera,
						
						// 3)
						left.coveredNoCamera + right.coveredHasCamera)
				);
		
		
		// x下方的点都被covered，x也被cover，且x上有相机
		long coveredHasCamera = Math.min(
				
				left.uncovered, 
				Math.min(
						left.coveredNoCamera, 
						left.coveredHasCamera)
				)
				
				
				+
				Math.min(
						right.uncovered, 
						Math.min(
								right.coveredNoCamera, 
								right.coveredHasCamera))
				
				
				+ 1;
		
		
		return new Info(uncovered, coveredNoCamera, coveredHasCamera);
	}

	public static int minCameraCover2(Node root) {
		Data data = process2(root);
		return data.cameras + (data.status == Status.UNCOVERED ? 1 : 0);
	}

	// 以x为头，x下方的节点都是被covered，x自己的状况，分三种
	public static enum Status {
		UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA
	}

	// 以x为头，x下方的节点都是被covered，得到的最优解中：
	// x是什么状态，在这种状态下，需要至少几个相机
	public static class Data {
		public Status status;
		public int cameras;

		public Data(Status status, int cameras) {
			this.status = status;
			this.cameras = cameras;
		}
	}

	public static Data process2(Node X) {
		if (X == null) {
			return new Data(Status.COVERED_NO_CAMERA, 0);
		}
		Data left = process2(X.left);
		Data right = process2(X.right);
		int cameras = left.cameras + right.cameras;
		if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
			return new Data(Status.COVERED_HAS_CAMERA, cameras + 1);
		}
		
		
		// 左右孩子，不存在没被覆盖的情况
		if (left.status == Status.COVERED_HAS_CAMERA 
				|| 
				right.status == Status.COVERED_HAS_CAMERA) {
			return new Data(Status.COVERED_NO_CAMERA, cameras);
		}
		// 左右孩子，不存在没被覆盖的情况，也都没有相机
		return new Data(Status.UNCOVERED, cameras);
	}

}
