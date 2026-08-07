package com.zaqbest.study.alg.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155：最小栈
 * 算法描述：使用辅助栈保存最小值
 */
public class Problem_0155_MinStack {

    Deque<Integer> stack;
    Deque<Integer> minStack;
    /** initialize your data structure here. */
    public Problem_0155_MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(minStack.peek(), val));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}