package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.TreeNode;

/**
 * 104: 二叉树的最大深度
 */
public class Problem_0104_MaxDepth {
    public int maxDepth(TreeNode root){
        if (root == null) return 0;
        else return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
