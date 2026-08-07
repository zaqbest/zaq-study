package com.zaqbest.study.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 78：子集
 * 问题描述：
 * https://leetcode-cn.com/problems/subsets/
 */
public class Problem_0078_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());//空集

        if (nums == null || nums.length == 0){
            return result;
        }

        for (int n: nums){
            List<List<Integer>> temp = new ArrayList<>();
            //遍历result中的每一个元素，和新的数字组成的集合放回去
            for (List<Integer> s: result){
                temp.add(s);

                //重新复制一份，否则是对象的引用
                List<Integer> anotherList = new ArrayList<>(s);
                anotherList.add(n);
                temp.add(anotherList);
            }
            //将增加了一位数字的子集放入
            result.clear();
            result.addAll(temp);
        }
    return result;
    }
}
