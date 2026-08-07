package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.Node;

/**
 * 142: 环形链表II
 *
 *
 * <p>{@link Problem_0141_HasCycle#hasCycle(ListNode)}
 *
 * <p>
 *     算法描述：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
 * </p>
 */
public class Problem_0142_DetectCycle {
    public Node detectCycle(Node head) {
        if (head == null || head.next == null)
            return null;

        Node fast = head.next.next;
        Node slow = head.next;

        while (slow != fast){
            if (fast == null || fast.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }
}
