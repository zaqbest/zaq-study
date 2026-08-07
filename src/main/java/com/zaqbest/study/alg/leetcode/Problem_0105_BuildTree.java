package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.TreeNode;

/**
 *
 * 105：从前序与中序遍历序列构造二叉树
 * 问题描述：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class Problem_0105_BuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length,
                inorder, 0, inorder.length);
    }

    /**
     * 注意：start, end => [start, end) 含左不含右
     *
     * @param preorder
     * @param p_start
     * @param p_end
     * @param inorder
     * @param i_start
     * @param i_end
     * @return
     */
    private TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end,
                                    int[] inorder, int i_start, int i_end){
        if (p_start == p_end)
            return null;
        int root_val = preorder[p_start];
        TreeNode root = new TreeNode(root_val);
        //在中序遍历中找到根节点的位置
        int i_root_index = 0;
        for (int i = i_start; i < i_end; i++) {
            if (root_val == inorder[i]) {
                i_root_index = i;
                break;
            }
        }

        int leftNum = i_root_index - i_start;
        //递归的构造左子树
        root.left = buildTreeHelper(preorder, p_start + 1, p_start + leftNum + 1, inorder, i_start, i_root_index);
        //递归的构造右子树
        root.right = buildTreeHelper(preorder, p_start + leftNum + 1, p_end, inorder, i_root_index + 1, i_end);
        return root;
    }
}
