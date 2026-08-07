package com.zaqbest.study.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Problem_0017_LetterCombination {

    public static void main(String[] args) {
        List<String> letterCombination = letterCombinations("23");
        for (String s : letterCombination){
            System.out.println(s);
        }
    }

    public static List<String> letterCombinations(String digits) {
        //数字字母映射表
        Map<Character, List<Character>> map = new HashMap<Character, List<Character>>(){{
            put('2', Arrays.asList('a', 'b', 'c'));
            put('3', Arrays.asList('d', 'e', 'f'));
            put('4', Arrays.asList('g', 'h', 'i'));
            put('5', Arrays.asList('j', 'k', 'l'));
            put('6', Arrays.asList('m', 'n', 'o'));
            put('7', Arrays.asList('p', 'q', 'r', 's'));
            put('8', Arrays.asList('t', 'u', 'v'));
            put('9', Arrays.asList('w', 'x', 'y', 'z'));

        }};

        //输入为空时，返回空列表
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0){
            return result;
        }

        //遍历每一个字母
        for (Character digitChar : digits.toCharArray()){
            List<String> tempList = new ArrayList<>();
            //获取数字对应的所有字母
            for (Character listChar: map.get(digitChar)){

                //如果处理前队列为空，直接添加当前字母
                if (result.size() == 0){
                    tempList.add(String.valueOf(listChar));
                } else{
                   Iterator<String> iterator = result.listIterator();
                   while (iterator.hasNext()){
                        tempList.add(iterator.next().concat(String.valueOf(listChar)));
                    }
                }
            }

            result.clear();
            result.addAll(tempList);
        }

        return result;
    }

}
