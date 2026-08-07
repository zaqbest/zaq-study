package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class07.Code02_RecoverBST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 99. 恢复二叉搜索树
 * <p>
 * {@link Code02_RecoverBST}
 */
public class Problem_0099_RecoverTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void recoverTree(TreeNode head) {
        if (head == null){
            return;
        }

        TreeNode[] nodes = new TreeNode[2];
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (!stack.isEmpty() || head != null){
            if (head != null){
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && pre.val > head.val){
                    nodes[0] = nodes[0] == null ? pre : nodes[0];
                    nodes[1] = head;
                }

                pre = head;
                head = head.right;
            }
        }

        if (nodes[0] != null && nodes[1] != null){
            int tmp = nodes[0].val;
            nodes[0].val = nodes[1].val;
            nodes[1].val = tmp;
        }
    }


    //for test
    public static TreeNode createTree(Integer[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        int len = nums.length;
        int index = 0;

        TreeNode head = new TreeNode(nums[index]);
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(head);

        TreeNode cur;
        while (index < len) {
            index++;
            if (index >= len) return head;
            cur = nodeQueue.poll();
            Integer left = nums[index];
            if (left != null) {
                cur.left = new
                        TreeNode(left);
                nodeQueue.offer(cur.left);
            }

            index++;
            if (index >= len) return head;
            Integer right = nums[index];
            if (right != null) {
                cur.right = new
                        TreeNode(right);
                nodeQueue.offer(cur.right);
            }
        }
        return head;
    }

    //for test
    private static int treeNodeCount(TreeNode head){
        if (head == null){
            return 0;
        } else {
            return 1+treeNodeCount(head.left) + treeNodeCount(head.right);
        }
    }

    //for test
    private static void printTreeNode(TreeNode head){
        if (head == null) {
            return;
        }
        int count = treeNodeCount(head);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {

            TreeNode cur = queue.poll();
            if (cur == null){
                System.out.print("null ");
                continue;
            } else {
                System.out.print(cur.val + " ");
                if (--count <= 0){
                    break;
                }
            }
            queue.add(cur.left);
            queue.add(cur.right);
        }
    }

    public static void main(String[] args) {
        TreeNode head = createTree(new Integer[]{3,1,4,null,null,2});
        recoverTree(head);
        printTreeNode(head);

    }
}
