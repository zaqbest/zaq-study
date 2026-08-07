package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.Node;

/**
 * 问题描述
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 *
 * 处理思路：分解成两个列表的合并
 */
public class Problem_0023_MergeKLists {
    public Node mergeKLists(Node[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        return mergeKLists(lists, 0, lists.length -1);
    }

    private Node mergeKLists(Node[] lists, int start, int end){
        //一个节点的情况
        if (start == end){
            return lists[start];
        }
        //两个节点的情况
        else if (end - start == 1){
            return mergeTwoLists(lists[start], lists[end]);
        }
        //超过两个节点
        else {
          int mid = (start + end) / 2;
            Node left = mergeKLists(lists, start, mid);
            Node right = mergeKLists(lists, mid+1, end);
         return mergeTwoLists(left, right);
        }
    }

    private Node mergeTwoLists(Node l1, Node l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        Node dummy = new Node();
        Node l1Ptr = l1;
        Node l2Ptr = l2;
        Node dummyPtr = dummy;

        while (l1Ptr != null && l2Ptr != null){
            //L1节点比较小
            if (l1Ptr.val <= l2Ptr.val){
                dummyPtr.next = l1Ptr;

                dummyPtr = dummyPtr.next;
                l1Ptr = l1Ptr.next;
            } else{
                dummyPtr.next = l2Ptr;
                dummyPtr = dummyPtr.next;
                l2Ptr = l2Ptr.next;
            }
        }

        if (l1Ptr != null){
            dummyPtr.next = l1Ptr;
        }

        if (l2Ptr != null){
            dummyPtr.next = l2Ptr;
        }

        return dummy.next;
    }
}
