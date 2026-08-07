package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.Node;

/**
 * 合并两个有序列表
 * 参考链接 <a>https://leetcode-cn.com/problems/merge-two-sorted-lists/</a>
 */
public class Problem_0021_MergeTwoList {
    public Node mergeTwoLists(Node l1, Node l2) {
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
