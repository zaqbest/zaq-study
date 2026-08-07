package com.zaqbest.study.alg.mihoyo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 输入一个字符串，求最长的包含且只包含1807这四个字符且按1→8→0→7顺序的子串的长度。
 *
 * 时间复杂度
 * O(N)
 *
 * 空间复杂度
 * O(N)
 *
 * 可以把dp缓存变量，空间复杂度优化为O(1)
 */
public class Problem_MaxLenghtContains1807 {
    public static int maxLength(String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        Map<Character, Integer> seq = new HashMap<>();
        seq.put('1', 1);
        seq.put('8', 2);
        seq.put('0', 3);
        seq.put('7', 4);

        List<Character> limit = Arrays.asList('1','8','0','7');

        int[] dp = new int[s.length()];
        char[] str = s.toCharArray();
        int ans = 0;
        dp[0] = limit.contains(str[0]) ? 1 : 0;
        ans = Math.max(ans, dp[0]);
        for (int i =1; i < str.length; i++){
            if (limit.contains(str[i])){
                if (limit.contains(str[i-1]) && seq.get(str[i]) >= seq.get(str[i-1])){
                    dp[i] = dp[i-1] + 1;
                    ans = Math.max(ans, dp[i]);
                } else {
                    dp[i] = 1;
                    ans = Math.max(ans, dp[i]);
                }
            } else {
                dp[i] = 0;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String s = "111807";
        System.out.println(maxLength(s));
        s = "111x1807";
        System.out.println(maxLength(s));
        s = "111x180777777777777777";
        System.out.println(maxLength(s));
        s = "s1807b1881800777777q";
        System.out.println(maxLength(s));
    }
}
