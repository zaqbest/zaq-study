package com.zaqbest.study.alg.leetcode;

/**
 * 0007 整数翻转
 *
 * <a>https://leetcode-cn.com/problems/reverse-integer/solution/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/</a>
 */
public class Problem_0007_Reverse {
    public static int reverse(int x) {
        int res = 0;
        while(x!=0) {
            //每次取末尾数字
            int tmp = x%10;

            //溢出判断（666）
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10)  {
                return 0;
            }
            res = res*10 + tmp;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {

        int i = -100023;
        System.out.println(reverse(i));

        System.out.println(-5%10);
    }
}
