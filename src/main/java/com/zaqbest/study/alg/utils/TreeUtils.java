package com.zaqbest.study.alg.utils;

import com.zaqbest.study.alg.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树算法辅助列
 */
public class TreeUtils {

    /**
     * 根据先序遍历和中序遍历构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
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
    private static TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end,
                                            int[] inorder, int i_start, int i_end) {
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

    /**
     * 将一个一维数组转换为二叉树
     *
     * @param array
     * @return
     */
    public static TreeNode buildTree(Integer[] array){
        if(array.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        boolean isLeft = true;
        for(int i = 1; i < array.length; i++){
            TreeNode node = queue.peek();
            if(isLeft){
                if(array[i] != null){
                    node.left = new TreeNode(array[i]);
                    queue.offer(node.left);
                }
                isLeft = false;
            }else {
                if(array[i] != null){
                    node.right = new TreeNode(array[i]);
                    queue.offer(node.right);
                }
                queue.poll();
                isLeft = true;
            }
        }
        return root;
    }

    /**
     * 查找二叉树中的指定节点
     *
     * @param root
     * @param target
     * @return
     */
    public static TreeNode findNode(TreeNode root, Integer target){
        if (root == null) return null;
        if (root.val == target) return root;

        TreeNode leftResult = findNode(root.left, target);
        if (leftResult != null) return leftResult;

        TreeNode rightResult = findNode(root.right, target);
        return rightResult;
    }
}
