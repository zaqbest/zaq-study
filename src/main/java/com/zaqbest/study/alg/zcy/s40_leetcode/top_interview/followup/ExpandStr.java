package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.followup;

/**
 * 课外练习题
 *
 * 展开字符串
 * aa2(b)=>aabb
 * 3(aa4(bb))=> aabbbbbbbbaabbbbbbbbaabbbbbbbb
 *
 * 思路
 * - 递归套路  ()里的内容交给子过程处理
 */
public class ExpandStr {
    public static String expandStr(String s){
        return value(s.toCharArray(), 0).res;
    }

    static class Info{
        public String res;
        public int index;

        public Info(String r, int i){
            res = r;
            index = i;
        }
    }

    private static Info value(char[] str, int index){
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        while (index < str.length && str[index] != ')'){
            if (str[index] >= '0' && str[index] <= '9'){
                cur = cur * 10 + str[index++] - '0';
            } else if (str[index] == '('){
                Info info = value(str, index+1);
                for (int i = 0; i < cur; i++) {
                    sb.append(info.res);
                }
                index = info.index + 1;
            } else {
                sb.append(str[index++]);
            }
        }

        return new Info(sb.toString(), index);
    }

    public static void main(String[] args) {
        String s = "3(aa4(bb))";
        String res=  expandStr(s);
        System.out.println(res);
    }
}
