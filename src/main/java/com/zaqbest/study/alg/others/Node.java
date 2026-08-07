package com.zaqbest.study.alg.others;

public class Node {
    int value;//本节点的值
    Node left;//左边的子节点
    Node right;//右边的子节点

    Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}
