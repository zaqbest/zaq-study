package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class08.Code01_RegularExpressionMatch;

/**
 * 10 正则表达式匹配
 *
 * <a>https://leetcode-cn.com/problems/regular-expression-matching/</a>
 *
 * <p>
 * 正则匹配
 * {@link Code01_RegularExpressionMatch}
 * </p>
 */
public class Problem_0010_IsMatch {
    public static boolean isValid(char[] s, char[] e) {
        // s中不能有'.'  or  '*'
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        // 开头的e[0]不能是'*'，没有相邻的'*'
        for (int i = 0; i < e.length; i++) {
            if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) && process(s, e, 0, 0);
    }

    // e[ei....]  能否变成  s[si...]
    // 重要限制：e[ei]不能压中'*'
    public static boolean process(char[] s, char[] e, int si, int ei) {
        if (ei == e.length) { // base case   exp已经耗尽了  ""
            return si == s.length;
        }
        // si == s.length  没有讨论
        // exp[ei]有字符的   exp[ei] != "*"
        // 可能性一，ei+1位置，不是*
        if (ei + 1 == e.length || e[ei + 1] != '*') {
            // s[si...]必须有东西  &&   s[si]  e[ei]   && 后续还得能对上
            return si != s.length
                    && (e[ei] == s[si] || e[ei] == '.')
                    && process(s, e, si + 1, ei + 1);
        }
        // ei + 1 位置是*
        // 尝试  [ei][ei+1]共同的部分，匹配str可能的前缀
        //s=aaaabc, e=a*bc
        //1）while si依次为0,1,2,3, 分别尝试了0个a,1个a,2个a,3个a的情况
        while (si != s.length && (e[ei] == s[si] || e[ei] == '.')) {
            if (process(s, e, si, ei + 2)) {//ei+2是跳过*
                return true;
            }
            si++;
        }
        //2）这里si为4，尝试4个a的情况
        return process(s, e, si, ei + 2);
    }
}
