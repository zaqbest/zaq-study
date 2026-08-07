package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.TreeNode;

/**
 * 226:翻转二叉树
 */
public class Problem_0226_InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return root;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }
}
