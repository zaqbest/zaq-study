package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.TreeNode;

/**
 * 98: 验证二叉查找树
 * 问题描述：https://leetcode-cn.com/problems/validate-binary-search-tree/
 *
 * 最大值和最小值使用Long型，避免移除
 */
public class Problem_0098_IsValidBST {
    public boolean isValidBST(TreeNode root) {
        if (root == null )
            return true;

        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long floor, long ceil){

        if (root == null)
            return true;
        if (root.val <= floor || root.val >= ceil)
            return false;

        return isValidBST(root.left, floor, root.val)
                && isValidBST(root.right, root.val, ceil);
    }
}
