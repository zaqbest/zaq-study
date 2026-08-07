package com.zaqbest.study.alg.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个括号，判断是否有效
 * <a>https://leetcode-cn.com/problems/valid-parentheses/</a>
 */
public class Problem_0020_ValidParentheses {

    public static void main(String[] args) {
        boolean result = new Problem_0020_ValidParentheses().isValid("([)]");
        System.out.println(result);
    }

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<Character,Character>(){{
           put(')','(');
           put(']','[');
           put('}','{');
        }};

        //右半个括号
        List<Character> rightHalf = Arrays.asList(')',']','}');
        //空字符串认为有效
        if (s == null || s.length() == 0)
            return true;

        Deque<Character> stack = new ArrayDeque<>();

        for(char c: s.toCharArray()){
            //是右半个括号
            if (rightHalf.contains(c)){
                if (stack.size() == 0)
                    return false;
                else{
                    //栈顶的元素为对应的左半个括号,继续处理
                    if(map.get(c) == stack.removeLast())
                        continue;
                    else
                        return false;
                }
            } else{
                //做半个括号，直接放入栈
                stack.add(c);
            }
        }

        //处理完成后栈必须为空
        return stack.size() == 0;
    }
}
