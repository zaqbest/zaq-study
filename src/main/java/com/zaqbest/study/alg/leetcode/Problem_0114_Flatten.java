package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.TreeNode;

/**
 * 114:二叉树展开为列表
 */
public class Problem_0114_Flatten {
    public void flatten(TreeNode root) {
        if (root == null) return;

        //先处理右子树
        if (root.right != null){
            flatten(root.right);
        }

        //左子树为空的情况，不需要处理
        if (root.left != null){
            //处理左子树
            flatten(root.left);

            //获取左子树的最后一个非null节点
            TreeNode lastNodeOfLeft = root.left;
            while (lastNodeOfLeft.right != null) lastNodeOfLeft = lastNodeOfLeft.right;

            lastNodeOfLeft.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }
}
