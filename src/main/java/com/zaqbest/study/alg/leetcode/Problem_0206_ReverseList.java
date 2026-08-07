package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.Node;

/**
 * 206:反转链表
 *
 * 算法描述：https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/
 *
 * 双指针迭代
 * 我们可以申请两个指针，第一个指针叫 pre，最初是指向 null 的。
 * 第二个指针 cur 指向 head，然后不断遍历 cur。
 * 每次迭代到 cur，都将 cur 的 next 指向 pre，然后 pre 和 cur 前进一位。
 * 都迭代完了(cur 变成 null 了)，pre 就是最后一个节点了。
 */
public class Problem_0206_ReverseList {
    public Node reverseList(Node head) {
        Node pre = null;
        Node curr = head;

        while (curr != null){
            Node tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }

        return pre;
    }
}
