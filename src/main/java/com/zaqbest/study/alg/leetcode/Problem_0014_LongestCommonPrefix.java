package com.zaqbest.study.alg.leetcode;

/**
 * 0014 最长公共前缀
 */
public class Problem_0014_LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs ==null || strs.length ==0){
            return "";
        }

        if (strs.length == 1){
            return strs[0];
        }

        String ans = strs[0];
        for (int i = 1; i < strs.length; i++){
            ans  = common(ans, strs[i]);
            if (ans.length() == 0){
                break;
            }
        }

        return ans;
    }

    private static String common(String s1, String s2){
        int len = s1.length() < s2.length() ? s1.length(): s2.length();

        int i;
        for (i =0; i < len; i++){
            if (s1.charAt(i) != s2.charAt(i)){
                break;
            }
        }

        return s1.substring(0, i);
    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};

        String res = longestCommonPrefix(strs);
        System.out.println(res);
    }
}
