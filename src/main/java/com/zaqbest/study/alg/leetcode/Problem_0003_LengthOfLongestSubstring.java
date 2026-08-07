package com.zaqbest.study.alg.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 *
 * 从左往右的尝试模型
 */
public class Problem_0003_LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }

        int ans = 0;
        for (int i = 0; i < s.length(); i++){
            Set<Character> set = new HashSet<>();
            int sum = 0;
            for (int j = i; j >= 0; j--){
                if (!set.contains(s.charAt(j))){
                    sum++;
                    set.add(s.charAt(j));
                } else{
                    break;
                }
            }

            ans = Math.max(ans, sum);
        }

        return ans;
    }

    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }

        if (s.length() == 1){
            return 1;
        }

        char[] chars = s.toCharArray();
        int N = s.length();
        int ans = 1;
        //dp[i] 指的是0..i,并且以i结尾，最长不重复子字符串长度
        int[] dp = new int[N];
        dp[0] = 1;
        Map<Character, Integer> posMap = new HashMap<>();
        posMap.put(chars[0], 0);
        for (int i = 1; i < N; i++){
            if (!posMap.containsKey(chars[i])){
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = Math.min(i - posMap.get(chars[i]), dp[i-1]+1);
            }
            ans = Math.max(ans, dp[i]);
            posMap.put(chars[i], i);
        }

        return ans;
    }

    public static void main(String[] args) {
        String s = "fasfasdfasfasdfasdfadfasdf";
        int r1 = lengthOfLongestSubstring1(s);
        int r2 = lengthOfLongestSubstring2(s);

        System.out.println(r1);
        System.out.println(r2);
    }
}
