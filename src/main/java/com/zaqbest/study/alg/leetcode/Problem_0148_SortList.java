package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.Node;
import com.zaqbest.study.alg.utils.NodeUtil;

import java.util.Arrays;

/**
 * 148. 排序链表
 *
 * 问题描述：https://leetcode-cn.com/problems/sort-list/
 * 问题算法：
 * 归并排序
 */
public class Problem_0148_SortList {
    public static void main(String[] args) {
        Node head = NodeUtil.buildList(Arrays.asList(-1,5,3,4,0,2,3,45,67,11,1));
        Node result = new Problem_0148_SortList().sortList(head);
        NodeUtil.printList(result);
    }
    public Node sortList(Node head) {
        if (head == null || head.next == null)
            return head;

        //p节点每次走两步，q节点每次走一步
        //则p走到终点时，q为中间节点
        Node fast = head.next;
        Node slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        //对左右子链表分别排序
        Node right = sortList(slow.next);
        slow.next = null;
        Node left = sortList(head);

        //对排序后的子列表进行排序
        Node h = new Node();
        Node res = h;
        while (left != null  && right != null){
            if (left.val < right.val){
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }

            //公共部分统一处理
            h = h.next;
        }

        //处理后续的列表!!!!
        h.next = left != null ? left : right;

        return res.next;
    }

}
