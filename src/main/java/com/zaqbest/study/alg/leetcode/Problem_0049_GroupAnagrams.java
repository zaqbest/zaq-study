package com.zaqbest.study.alg.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode49:分组输出异位词
 * 问题描述：https://leetcode-cn.com/problems/group-anagrams/
 */
public class Problem_0049_GroupAnagrams {
    public static void main(String[] args) {
        Problem_0049_GroupAnagrams solution = new Problem_0049_GroupAnagrams();
        List<List<String>> result = solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(result);

    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null){
            return result;
        }

        Map<String, List<String>> map = new HashMap<>();

        //按照排序后的词放入hashmap
        for (String s: strs){
            String sortedStr = getSortedStr(s);
            if (map.containsKey(sortedStr)){
                map.get(sortedStr).add(s);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(s);
                map.put(sortedStr, temp);
            }
        }

        //遍历hash表，输出结果
        for(Map.Entry<String, List<String>> entry: map.entrySet()){
            result.add(entry.getValue());
        }

        return result;
    }

    private String getSortedStr(String s){
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
