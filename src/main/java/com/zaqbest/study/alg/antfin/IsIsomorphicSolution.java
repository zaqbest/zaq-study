package com.zaqbest.study.alg.antfin;

import java.util.HashMap;
import java.util.Map;

/**
 * 205：判断两个词是否是同构（唯一映射）
 *
 * abb->cdd 同构
 * abb ->ade 非同构
 *
 * 问题描述
 * https://leetcode-cn.com/problems/isomorphic-strings/
 *
 * 算法描述
 * https://leetcode-cn.com/problems/isomorphic-strings/solution/tong-gou-zi-fu-chuan-by-leetcode-solutio-s6fd/
 */
public class IsIsomorphicSolution {
    public boolean isIsomorphic(String s, String t){
        if (s == null || t == null)
            return false;

        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();

        for (int i = 0; i < s.length(); i++){
            Character x = s.charAt(i);
            Character y = t.charAt(i);
            if (s2t.containsKey(x) && s2t.get(x) != y)
                return false;
            if (t2s.containsKey(y) && t2s.get(y) != x)
                return false;
            s2t.put(x,y);
            t2s.put(y, x);
        }

        return true;
    }
}
