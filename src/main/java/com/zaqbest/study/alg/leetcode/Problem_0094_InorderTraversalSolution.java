package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 94:二叉树的中序遍历
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 */
public class Problem_0094_InorderTraversalSolution {

    public static void main(String[] args) {
        Problem_0105_BuildTree problem0105BuildTree = new Problem_0105_BuildTree();
       TreeNode tree = problem0105BuildTree.buildTree(new int[]{1,2,3}, new int[]{1,3,2});
       List<Integer> result = new Problem_0094_InorderTraversalSolution().inorderTraversal(tree);
        System.out.println(result);

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    private void inorderTraversal(TreeNode root, List<Integer> result){
        if (root == null)
            return;
        if (root.left != null){
            inorderTraversal(root.left, result);
        }
        result.add(root.val);
        if (root.right != null){
            inorderTraversal(root.right, result);
        }
    }
}
