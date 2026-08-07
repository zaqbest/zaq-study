package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 297. 二叉树的序列化与反序列化
 */
public class Problem_0297_BTreeCodec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return toString(printTree(root));
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return createTree(parse(data));
    }

    /**
     * 创建一棵树
     *
     * @param data
     * @return
     */
    public static TreeNode createTree(Integer[] data){
        TreeNode root;

        if (data.length == 0)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        root = new TreeNode(data[0]);
        queue.add(root);
        int curIndex = 1;

        while (!queue.isEmpty() && curIndex < data.length){
            TreeNode curNode = queue.remove();

            Integer n =  data[curIndex++];
            curNode.left = n == null ? null : new TreeNode(n);

            if (curNode.left != null) queue.add(curNode.left);

            //右子树可能没有数据了，需要处理越界
            if (curIndex < data.length){
                n =  data[curIndex++];
                curNode.right = n == null ? null : new TreeNode(n);
                if (curNode.right != null) queue.add(curNode.right);
            }
        }

        return root;
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
}

