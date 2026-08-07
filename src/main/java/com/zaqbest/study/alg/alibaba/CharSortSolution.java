package com.zaqbest.study.alg.alibaba;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 按照字母的频率逆序打印字符串
 * 例如：abcaabcdde => aaabccdde
 */
public class CharSortSolution {
    public static void main(String[] args) {
        String res = new CharSortSolution().charSort("aaaabbcaabcdde");
        System.out.println(res);
    }
    public String charSort(String s){
        //字符串为空或者小于2个字符，不需要排序
        if (s == null || s.length() < 2){
            return s;
        }

        //统计每个字母的频次
        Map<Character, Integer> map = new HashMap<>();
        for (char c: s.toCharArray()){
            if (map.containsKey(c)){
                map.put(c, map.get(c).intValue()+1);
            } else{
                map.put(c, 1);
            }
        }

        //按照频次进行排序
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue() - o1.getValue());

        //输出结果
        StringBuffer result = new StringBuffer();
        for (Map.Entry<Character, Integer> e: list){
            for (int i = 0; i < e.getValue();i++){
                result.append(e.getKey());
            }
        }

        return result.toString();
    }
}
