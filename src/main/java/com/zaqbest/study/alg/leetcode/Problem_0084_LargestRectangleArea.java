package com.zaqbest.study.alg.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84：柱状图中最大的矩形
 */
public class Problem_0084_LargestRectangleArea {


    /**
     * 暴力计算
     * 算法描述：
     * 1，遍历每一个柱状
     * 2，向左和向右同时扩散，知道碰到更小的
     * 3，左右两边添加一个高度为0的柱状
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea_BruteForce(int[] heights) {

        if (heights == null || heights.length == 0){
            return 0;
        }
        int[] arr = new int[heights.length+2];
        for (int i = 0; i < heights.length;i++)
            arr[i+1] = heights[i];

        int res = 0;

        for (int i = 1; i < arr.length-1; i++){
            int h = arr[i];

            //找到左边界
            int leftBound = i;
            while (leftBound >= 0 && arr[leftBound] >= h) leftBound--;

            //找到右边界
            int rigthBound = i;
            while (rigthBound < arr.length && arr[rigthBound] >= h) rigthBound++;

            res = Math.max(res, h * (rigthBound - leftBound - 1));
        }

        return res;
    }


    /**
     * 线性栈算法
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0){
            return 0;
        }

        int len = heights.length;
        int[] arr = new int[len+2]; //前后增加高度为0的虚拟柱子
        int res = 0;
        for (int i = 0; i < len; i++)
            arr[i+1] = heights[i];

        Deque<Integer> stack = new ArrayDeque<>();
        int index = 1;
        stack.push(0);
        while (index < arr.length){
            System.out.println(stack.peek());
            while (index < arr.length && arr[index] >= arr[stack.peek()])
                stack.push(index++);
            while (index < arr.length && arr[index] < arr[stack.peek()]){
                int currHeight = arr[stack.pop()];
                res = Math.max(res, currHeight * (index - stack.peek() - 1));
            }
            stack.push(index);
        }

        return res;
    }
}
