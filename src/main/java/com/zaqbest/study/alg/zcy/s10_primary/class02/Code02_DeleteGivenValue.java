package com.zaqbest.study.alg.zcy.s10_primary.class02;

/**
 * 从链表中删除指定的值
 */

public class Code02_DeleteGivenValue {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 链表中删除指定值
	 *
	 * @param head
	 * @param num
	 * @return
	 */
	public static Node removeValue(Node head, int num) {
		//被删除的节点可能在表头
		while (head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		Node pre = head;
		Node cur = head;
		while (cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
	}

}
