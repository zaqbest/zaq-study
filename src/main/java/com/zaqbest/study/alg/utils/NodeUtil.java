package com.zaqbest.study.alg.utils;

import com.zaqbest.study.alg.common.Node;

import java.util.List;

public class NodeUtil {
    /**
     * 构造列表
     *
     * @param nodes
     * @return
     */
    public static Node buildList(List<Integer> nodes){
        Node dummyNode = new Node();
        Node currNode = dummyNode;

        for (Integer i : nodes){
            Node newNode = new Node(i);
            currNode.next = newNode;
            currNode = currNode.next;
        }

        return dummyNode.next;
    }

    /**
     * 生成随机列表
     *
     * @param len
     * @param maxValue 最大值
     * @return
     */
    public static Node generateRandomLinkedList(int len, int maxValue) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * (maxValue + 1)));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (maxValue + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    /**
     * 复制一个链表
     * @param head
     * @return
     */
    public static Node copyOfNode(Node head){
        if (head == null)
            return head;

        Node newHead = new Node(0);
        Node preNode = newHead;
        while (head != null){
            preNode.next = new Node(head.val);
            preNode = preNode.next;

            head = head.next;
        }

        return newHead.next;
    }

    /**
     * 比较两个链表
     *
     * @param head1
     * @param head2
     * @return
     *      true 一致
     *      false 不一致
     */
    public static Boolean compareNode(Node head1, Node head2){
        //两个都为空
        if (head1 == null && head2 == null)
            return true;

        //有一个不为空
        if (head1 == null || head2 == null){
            return false;
        }

        while (head1 != null && head2 != null){
            //发现有值不一样，返回false
            if (head1.val != head2.val) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }

        //循环结束后，应该两个都是为空；如果存在某个不为空，则两个链表不相等
        if (head1 != null && head2 != null){
            return false;
        }

        return true;
    }

    /**
     * 检查链表中是否包含某个值
     *
     * @param head
     * @param target
     * @return
     */
    public static boolean contains(Node head, int target){
        if (head == null)
            return false;

        while (head !=null ){
            if (head.val == target){
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 打印链表
     */
    public static void printList(Node head){
        System.out.print("======== Node 数据 =========\n");
        Node curr = head;
        while (curr != null){
            System.out.printf("%d ", curr.val);
            curr = curr.next;
        }
        System.out.println();
    }
}
