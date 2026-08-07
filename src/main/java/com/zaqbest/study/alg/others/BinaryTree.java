package com.zaqbest.study.alg.others;

import java.util.Stack;

public class BinaryTree {

    public Node root = null;

    public BinaryTree(int[] array, int index) {
        root = createBinaryTree(array, index);
    }
    // 创建二叉树
    private   Node createBinaryTree(int[] array, int index) {

        Node treeNode = null;
        if (index < array.length) {
            treeNode = new Node(array[index]);
            // 对于顺序存储的完全二叉树，如果某个节点的索引为index，其对应的左子树的索引为2*index+1，右子树为2*index+1
            treeNode.left = createBinaryTree(array, 2 * index + 1);
            treeNode.right = createBinaryTree(array, 2 * index + 2);
        }
        return treeNode;
    }

    private void showData(Node node) {
        System.out.print(node);
    }

    // 递归遍历二叉树

    // 先序遍历（前序遍历）
    public void preOrder(Node node) {
        if (node != null) {
            showData(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 中序遍历
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            showData(node);
            inOrder(node.right);
        }
    }
    // 后序遍历
    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            showData(node);
        }
    }

    // 非递归遍历二叉树
    // 前序遍历
    public void noRecursionPreOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
            while (!stack.empty()) {
                node = stack.pop();
                showData(node);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
    }

    // 中序遍历
    public void noRecursionInOrder(Node node) {
        Stack<Node> stack = new Stack<>();
        Node p = node;
        while (p != null || stack.size() > 0) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            if (stack.size() > 0) {
                p = stack.pop();
                showData(p);
                p = p.right;
            }
        }
    }

    //后序遍历 ,需要记录遍历过的节点
    public void noRecursionPostOrder(Node node)
    {
        Node pre=node;
        Stack<Node> stack=new Stack<>();
        while(node!=null)
        {
            for(;node.left!=null;node=node.left)
            {
                stack.push(node);
            }
            while(node!=null&&(node.right==null||node.right==pre))
            {
                showData(node);
                pre=node;
                if(stack.empty()) return;
                node=stack.pop();
            }
            stack.push(node);
            node=node.right;
        }
    }
}