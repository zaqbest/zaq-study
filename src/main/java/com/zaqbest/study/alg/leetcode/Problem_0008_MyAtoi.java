package com.zaqbest.study.alg.leetcode;

/**
 * 8,字符串转数字
 *
 * <a>https://leetcode-cn.com/problems/string-to-integer-atoi/</a>
 *
 * 垃圾题目
 */
public class Problem_0008_MyAtoi {
    public static int myAtoi(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        long ans = 0;
        boolean negative = false;
        int index =0;

        char[] chars = s.toCharArray();
        int N = chars.length;
        while (index < N && chars[index] == ' '){
            index++;
        }

        if (index < N && (chars[index] == '-' || chars[index] =='+')){

            if (chars[index] == '-') {
                negative = true;
            }
            index++;
        }
        while (index < N && chars[index] == '0'){
            index++;
        }

        while (index < N ){

            if (chars[index] < '0' || chars[index] > '9'){
                break;
            }

            ans = ans * 10 + (chars[index]-'0');

            if (ans > Integer.MAX_VALUE){

                if (negative){
                    ans = Integer.MIN_VALUE;
                } else {
                    ans = Integer.MAX_VALUE;
                }
                break;
            }

            index++;
        }

        return negative ? - (int) ans :(int)ans;
    }

    public static void main(String[] args) {
        String s1 = "            ";
        String s2 = "   -00000";
        String s3 = "  -000001";
        String s4 = "  - 000001";
        String s5 = "  00000111111111111111111111111111111111111111111111111111A";

        System.out.println(myAtoi(s1));
        System.out.println(myAtoi(s2));
        System.out.println(myAtoi(s3));
        System.out.println(myAtoi(s4));
        System.out.println(myAtoi(s5));
    }
}
