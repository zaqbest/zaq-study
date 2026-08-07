package com.zaqbest.study.alg.leetcode;

import java.util.List;

/**
 * 139: 单词拆分
 *
 * 存在问题：
 * 处理超时
 * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
 * ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
 * 提交时间	提交结果	运行时间	内存消耗	语言
 * 几秒前	超出时间限制	N/A	N/A	Java
 */
public class Problem_0139_WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0){
            return true;
        }

        for (String word: wordDict){
            //长度不足情况
            if (s.length() < word.length()) continue;
            //只需要一种情况成功即可
            if (word.equals(s.substring(0, word.length()))){
                if (wordBreak(s.substring(word.length()), wordDict)){
                    return true;
                }
            }
        }

        return false;
    }
}
