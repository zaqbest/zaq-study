package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

public class Problem_0019_RemoveNthNodeFromEndofList {

	public static class ListNode {
		public int val;
		public ListNode next;
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode cur = head;
		ListNode pre = null;
		while (cur != null) {
			n--;
			if (n == -1) {
				pre = head;
			}
			if (n < -1) {
				pre = pre.next;
			}
			cur = cur.next;
		}
		//节点数不够
		if (n > 0) {
			return head;
		}

		//要删除的节点是头结点
		if (pre == null) {
			return head.next;
		}

		//要删除的节点是中间一般节点
		pre.next = pre.next.next;
		return head;
	}

}
