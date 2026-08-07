package com.zaqbest.study.alg.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 301.删除无效的括号
 *
 * 输入：s = "()())()"
 * 输出：["(())()","()()()"]
 *
 * 自己独立完成一个困难的问题！
 * 思路：
 * 1，计算字符串总共有N个括号
 * 2，判断总共需要删除M个括号
 * 3，从N个括号中删除M个括号总共有X种方案
 * 4，判断每种方案处理完成之后是否是一个有效的括号
 */
public class Problem_0301_RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        Map<String, Integer> dp = new HashMap<>();
        int removeCount = removeCount(s, dp);
        if (removeCount == 0){
            return Arrays.asList(s);
        }

        //所有括号的下标
        List<Integer> bracketList = new ArrayList<>();
        for (int i=0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c =='(' || c ==')'){
                bracketList.add(i);
            }
        }

        List<List<Integer>> removeCombinationList = combination(bracketList.toArray(new Integer[bracketList.size()]), removeCount);

        Set<String> result = new HashSet<>();
        for (List<Integer> plan: removeCombinationList){
            String tmpStr = getStringAfterRemoveBracket(s, plan);
            if (0 == removeCount(tmpStr, dp)){
                result.add(tmpStr);
            }
        }
        return new ArrayList<>(result);

    }

    private String getStringAfterRemoveBracket(String s, List<Integer> bracketToRemoveList){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++){
            if (!bracketToRemoveList.contains(i)){
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }


    /**
     * 从一个数组中，选择N个数字，有多少种取法
     * @param arr 数组
     * @param targetSize 需要取多少个数字
     * @return
     */
    public List<List<Integer>> combination(Integer[] arr, int targetSize){
        List<List<Integer>> result = new ArrayList<>();
        combinationHelper(arr, targetSize, result, 0, null);

        return result;
    }

    private void combinationHelper(Integer[] arr, int targetSize, List<List<Integer>> result, int currIndex, List<Integer> midResult){
        if (midResult == null)
            midResult = new ArrayList<>();

        if (midResult.size() == targetSize){
            result.add(midResult);
            return;
        }

        if (currIndex > arr.length-1) {
            return;
        }

        combinationHelper(arr, targetSize, result, currIndex+1, new ArrayList<>(midResult));

        midResult.add(arr[currIndex]);
        combinationHelper(arr, targetSize, result, currIndex+1, new ArrayList<>(midResult));

    }

    /**
     * 计算所有需要移除的括号数
     *
     * @param s 输入字符串
     * @return
     */
    public int removeCount(String s, Map<String, Integer> dp){
        if (s == null || s.length() == 0)
            return 0;

        if (dp.containsKey(s))
            return dp.get(s);
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int removeCount = 0;
        for (char c: chars){
            if (c >= 'a' && c <= 'z'){
                continue;
            } else if (c == '('){
                stack.push(c);
            } else if (c ==')'){
                if (stack.isEmpty()) {
                    removeCount++;
                } else {
                    if (stack.peek() != '(' ){
                        removeCount++;
                    } else {
                        stack.pop();
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            stack.pop();
            removeCount++;
        }
        dp.put(s, removeCount);
        return removeCount;
    }

    public static void main(String[] args) {
        Problem_0301_RemoveInvalidParentheses solution = new Problem_0301_RemoveInvalidParentheses();
        List<String> result = solution.removeInvalidParentheses(")(");
//        System.out.println(JSONUtil.toJsonStr(result));
    }
}
