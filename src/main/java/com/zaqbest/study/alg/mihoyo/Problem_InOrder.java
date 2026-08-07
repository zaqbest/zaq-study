package com.zaqbest.study.alg.mihoyo;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串A(B(C,),D(,E))表示二叉树
 *
 * tree1.png。
 *
 * 求这类字符串表示的二叉树的中序遍历。
 */
public class Problem_InOrder {
    /**
     * 默认是一个合法的字符串，没有空格
     *
     * 例如： A(B(C,),D(,E))
     *
     * @param s
     * @return
     */
    public static List<Character> inOrder(String s){
        if (s == null || s.length() == 0){
            return new ArrayList<>();
        }

        List<Character> ans = new ArrayList<>();
        process(s.toCharArray(), 0, s.length()-1, ans);

        return ans;

    }

    private static void process(char[] str, int L, int R, List<Character> ans){

        if (L > R){
            return;
        }

        if (L == R){
            if (str[L] != ','){
                ans.add(str[L]);
            }
            return;
        }

        if (R - L == 1){
            ans.add(str[L] != ',' ? str[L] : str[R]);
            return;
        }

        int mid = findMid(str, L + 2, R-1);

        if (mid != -1){
            process(str, L+2, mid-1, ans);
            ans.add(str[L]);
            process(str, mid+1, R-1, ans);
        }
    }

    /**
     * 查找逗号位置
     *
     * @param str
     * @param L
     * @param R
     * @return
     */
    private static int findMid(char[] str, int L, int R){
        int count = 0;

        for (int i = L; i <= R; i++){
            if (str[i] == '('){
                count++;
            } else if (str[i] == ')'){
                count--;
            }

            if (str[i] == ',' && count == 0){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String s = "A(B(C,),D(,E))";
        List<Character> ans = inOrder(s);
        System.out.println(ans);
    }
}
