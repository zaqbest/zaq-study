package com.zaqbest.study.alg.common;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNodeUtil {
    /**
     * 创建一棵树
     *
     * @param nums
     * @return
     */
    public static TreeNode createTree(Integer[] nums){
        if(nums==null||nums.length==0)
            return null;
        int len=nums.length;
        int index=0;
        TreeNode head=new TreeNode(nums[index]);
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(head);
        TreeNode cur;
        while (index<len){
            index++;
            if (index>=len)return head;
            cur=nodeQueue.poll();
            Integer left=nums[index];
            if (left!=null){
                cur.left=new TreeNode(left);
                nodeQueue.offer(cur.left);
            }

            index++;
            if (index>=len)return head;
            Integer right=nums[index];
            if (right!=null){
                cur.right=new TreeNode(right);
                nodeQueue.offer(cur.right);
            }
        }
        return head;
    }

    /**
     * 打印一棵树
     *
     * @param root
     * @return
     */
    public static Integer[] printTree(TreeNode root){
        Deque<Integer> resList = new LinkedList<>();
        if (root == null)
            return resList.toArray(new Integer[resList.size()]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        resList.add(root.val);

        while (!queue.isEmpty()){
            TreeNode curNode = queue.remove();
            if (curNode.left != null){
                resList.add(curNode.left.val);
                queue.add(curNode.left);
            }else{
                resList.add(null);
            }

            if (curNode.right != null){
                resList.add(curNode.right.val);
                queue.add(curNode.right);
            }else{
                resList.add(null);
            }
        }

        //去除末尾所有的NULL
        while (!resList.isEmpty() && resList.peekLast() == null) resList.pollLast();

        return resList.toArray(new Integer[resList.size()]);
    }

    private static String toString(Integer[] nums){
        if (nums == null || nums.length ==0) return "";
        List<String> strList = new ArrayList<>();
        for (Integer n: nums){
            strList.add(String.valueOf(n));
        }

        return String.join(",", strList);
    }

    private static Integer[] parse(String input){
        List<Integer> intList = new ArrayList<>();
        if (input == null || input.length() == 0)
            return intList.toArray(new Integer[intList.size()]);

        for (String s: input.split(",")){
            intList.add("null".equals(s) ? null : Integer.valueOf(s));
        }

        return intList.toArray(new Integer[intList.size()]);
    }

    public static void main(String[] args) {
        TreeNode root = createTree(new Integer[]{1,2,3,null,null,4,5});
        Integer[] res = printTree(root);
        System.out.println(toString(parse(toString(res))));
    }
}
