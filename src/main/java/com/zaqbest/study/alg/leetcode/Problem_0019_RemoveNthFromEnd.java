package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.Node;
import com.zaqbest.study.alg.utils.NodeUtil;

import java.util.Arrays;

public class Problem_0019_RemoveNthFromEnd {

    public static void main(String[] args) {
        Node listNode = NodeUtil.buildList(Arrays.asList(1,2,3,4,5,6,7,8));
        listNode = removeNthFromEnd(listNode, 5);
        NodeUtil.printList(listNode);
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static Node removeNthFromEnd(Node head, int n) {

        if (head == null || n < 0){
            return head;
        }

        Node dummyNode = new Node();
        dummyNode.next = head;

        Node first = head;
        Node second = dummyNode;//n-1

        while (n-- > 0 && first != null){
            first = first.next;
        }

        //链表长度不够N
        if (n > 0 ){
            return head;
        }

        while (first != null){
            first = first.next;
            second = second.next;
        }

        //链表删除
        second.next = second.next.next;

        return dummyNode.next;
    }
}
