package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

/**
 * 检查链表是否有环
 *
 * 思路
 * - 快慢指针
 */
public class Problem_0141_LinkedListCycle {

	public static class ListNode {
		int val;
		ListNode next;
	}

	public static boolean hasCycle(ListNode head) {
		return getFirstLoopNode(head) != null;
	}

	public static ListNode getFirstLoopNode(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (slow != fast) {
			//一旦走到null, 就肯定不存在环
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

}
