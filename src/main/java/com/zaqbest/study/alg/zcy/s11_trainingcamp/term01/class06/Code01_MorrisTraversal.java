package com.zaqbest.study.alg.zcy.s11_trainingcamp.term01.class06;

/**
 * morris遍历二叉树
 * 遍历二叉树，时间复杂度O(n), 空间复杂度O(1)
 */
public class Code01_MorrisTraversal {
	
	public static class Node {
		public int value;
		Node left;
		Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * morris更容易理解的版本
	 *
	 * 先序： 有左树的情况，打印第1次访问；
	 * 中序： 有左树的情况，打印第2次访问
	 *
	 * @param head
	 */
	public static void morris(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight;
		while (cur != null) {
			mostRight = cur.left;
			//有左树（节点会被访问两次）
			if (mostRight != null) {
				//左树上，真实的最右节点
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					// 第1次访问
					//System.out.println("1:" + cur.value);
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					//第2次访问
					System.out.println("2:" + cur.value);
					cur = cur.right;
					continue;
				}
			} else {
				//没有左树（节点只会被访问1次）
				System.out.println("3:" + cur.value);
				cur = cur.right;
			}
		}
		System.out.println();
	}

	public static void morrisIn(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight;
		while (cur != null) {
			mostRight = cur.left;
			//有左树
			if (mostRight != null) {
				//左树上，真实的最右节点
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			}
			System.out.print(cur.value + " ");
			//没有左树
			cur = cur.right;
		}
		System.out.println();
	}

	public static void morrisPre(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					//向左移动之前打印cur
					System.out.print(cur.value + " ");
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
				}
			} else {
				System.out.print(cur.value + " ");
			}
			cur = cur.right;
		}
		System.out.println();
	}

	public static void morrisPos(Node head) {
		if (head == null) {
			return;
		}
		Node cur = head;
		Node mostRight;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					printEdge(cur.left);
				}
			}
			cur = cur.right;
		}
		printEdge(head);
		System.out.println();
	}

	public static void printEdge(Node head) {
		Node tail = reverseEdge(head);
		Node cur = tail;
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.right;
		}
		reverseEdge(tail);
	}

	public static Node reverseEdge(Node from) {
		Node pre = null;
		Node next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);
		head.right.right = new Node(7);
//		printTree(head);
//		morrisIn(head);
//		morrisPre(head);
		morrisPos(head);
//		printTree(head);
//		morris(head);
	}

}
