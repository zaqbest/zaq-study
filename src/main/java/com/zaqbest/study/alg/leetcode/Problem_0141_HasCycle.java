package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.Node;

/**
 * 141: 环形链表
 */
public class Problem_0141_HasCycle {
    public boolean hasCycle(Node head) {
        if (head == null || head.next == null)
            return false;

        Node fast = head.next.next;
        Node slow = head.next;

        while (slow != fast){
            if (fast == null || fast.next == null)
                return false;
            fast = fast.next.next;
            slow = slow.next;
        }

        return true;
    }
}
