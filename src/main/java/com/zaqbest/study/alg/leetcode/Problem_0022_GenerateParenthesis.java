package com.zaqbest.study.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * describe
 * https://leetcode-cn.com/problems/generate-parentheses/solution/
 *
 * solution
 * https://leetcode-cn.com/problems/generate-parentheses/solution/zui-jian-dan-yi-dong-de-dong-tai-gui-hua-bu-lun-da/
 */
public class Problem_0022_GenerateParenthesis {
    public static void main(String[] args) {
        List<String> result = new Problem_0022_GenerateParenthesis().generateParenthesis(0);
        System.out.println(result);

    }
    /**
     * 生成N组括号所有可能的组合
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {

        List<List<String>> result = new ArrayList<>();
        //n = 0的情况
        result.add(new ArrayList<String>(){{add("");}});
        //n = 1的情况
        result.add(new ArrayList<String>(){{add("()");}});

        if (n <= 0)
            return result.get(0);

        //n > 1的情况
        for (int i = 2; i <= n; i++){
            List<String> temp = new ArrayList<>();
            for(int p = 0; p < i; p++){
                int q = i -p -1;
                List<String> listP = result.get(p);
                List<String> listQ = result.get(q);

                for(String s1: listP){
                    for (String s2: listQ){
                        String str = "(" + s1 + ")" + s2;
                        temp.add(str);
                    }
                }
            }
            result.add(temp);
        }

        return result.get(n);
    }
}
