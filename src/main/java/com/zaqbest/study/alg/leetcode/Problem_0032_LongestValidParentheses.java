package com.zaqbest.study.alg.leetcode;

import java.util.Stack;

/**
 * 32. 最长有效括号
 */
public class Problem_0032_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return 0;

        //用于存储每一个'('对应的')'位置信息，如果没有找到对应则为-1
        int[] pos = new int[s.length()];
        Stack<Integer> stack = new Stack<>();

        //计算每一个左括号对应的右括号
        for(int i = 0; i < s.length(); i++){
            if (stack.isEmpty()){
                if (s.charAt(i) == ')')
                    pos[i] = -1;
                else{
                    stack.push(i);
                }
            } else {
                if (s.charAt(i) == '(') {
                    stack.push(i);
                }
                else {
                    int tmp = stack.pop();
                    pos[i] = tmp;
                    pos[tmp] = i;
                }
            }
        }

        while (!stack.isEmpty())
            pos[stack.pop()] = -1;

        //计算最大值
        int max = 0;
        for (int i = 0; i < pos.length; i++){
            //跳过前面的无效')'
            if (pos[i] == -1) continue;
            if (s.charAt(i) == '(' && pos[i] > 0){
                int curmax = 0;
                while (i < pos.length && s.charAt(i) == '(' && pos[i] > 0) {
                    curmax  = curmax +  pos[i] - i + 1;
                    i = pos[i] + 1;
                }

                max = Math.max(max, curmax);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Problem_0032_LongestValidParentheses solution = new Problem_0032_LongestValidParentheses();
        int s = solution.longestValidParentheses(")()()");
        System.out.println(s);
    }
}
