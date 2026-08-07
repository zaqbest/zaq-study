package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.zcy.s11_trainingcamp.term05.class01.Problem02_MinWindowLength;

/**
 * 0076. 最小覆盖子串
 *
 * 滑动窗口!!!
 *
 * 1143. 最长公共子序列
 * {@link Problem_1143_LongestCommonSubsequence}
 *
 * {@link Problem02_MinWindowLength}
 */
public class Problem_0076_MinWindow {
    public static String minWindow(String s, String t) {

        if (s == null || s.length() ==0 || t == null || t.length() == 0){
            return "";
        }

        int[] need = new int[128];
        for (int i = 0; i < t.length(); i++){
            need[t.charAt(i)]++;
        }
        int l = 0, r = 0, size = Integer.MAX_VALUE, start = 0, count = t.length();

        while (r < s.length()){
            char c = s.charAt(r);
            if (need[c] > 0){
                count--;
            }
            need[c]--;//右侧的字符加入窗口

            if (count == 0){//窗口已经包含了所有的字符
                while (l < r && need[s.charAt(l)] < 0){
                    need[s.charAt(l)]++;
                    l++;
                }

                //收集答案
                if (r -l +1 < size){
                    size = r -l + 1;
                    start = l;
                }

                //l向右移动后窗口肯定不能满足了 重新开始循环
                need[s.charAt(l)]++;
                count++;
                l++;
            }

            r++;
        }
        return size == Integer.MAX_VALUE ? "" : s.substring(start, start+size);
    }


    public static void main(String[] args) {
        String res = minWindow("ADOBECODEBANC","ABC");
        System.out.println(res);
    }

}
