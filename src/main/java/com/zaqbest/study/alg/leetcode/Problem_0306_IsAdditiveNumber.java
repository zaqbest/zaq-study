package com.zaqbest.study.alg.leetcode;

/**
 * 是否是累加数
 *
 * long型最大可以有19位
 */
public class Problem_0306_IsAdditiveNumber {

    /**
     * 存在问题：long会溢出
     * @param num
     * @return
     */
    public static boolean isAdditiveNumber1(String num) {
        return dfs(num, 0, 0, 0, 0);
    }

    private static boolean dfs(String num, int index, int count, long prevprev, long prev) {
        if (index >= num.length()) {
            return count > 2;
        }
        long current = 0;
        for (int i = index; i < num.length(); i++) {
            char c = num.charAt(i);

            if (num.charAt(index) == '0' && i > index) {
                // 剪枝1：不能做为前导0，但是它自己是可以单独做为0来使用的
                return false;
            }

            current = current * 10 + c - '0';

            if (count >= 2) {
                long sum = prevprev + prev;
//                if (sum < 0) {
//                    System.out.println("overflow");
//                }
                if (current > sum) {
                    // 剪枝2：如果当前数比之前两数的和大了，说明不合适
                    return false;
                }
                if (current < sum) {
                    // 剪枝3：如果当前数比之前两数的和小了，说明还不够，可以继续添加新的字符进来
                    continue;
                }
            }

            // 当前满足条件了，或者还不到两个数，向下一层探索
            if (dfs(num, i + 1, count + 1, prev, current)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isAdditiveNumber2(String num){
        for (int n1Len = 1; n1Len < num.length(); n1Len++){
            for (int n2Len = 1; n2Len < num.length(); n2Len++){
                if (f(num, n1Len, n2Len)){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     *
     * @param num
     * @param n1Len 第一个数的占用长度
     * @param n2Len 第二个数占用长度
     * @return
     */
    private static boolean f(String num, int n1Len, int n2Len){
        if (n1Len + n2Len >= num.length()){
            return false;
        }
        String s1 = num.substring(0, n1Len);
        String s2 = num.substring(n1Len, n1Len + n2Len);

        //不能0开头
        if ((s1.startsWith("0") && s1.length() > 1) || (s2.startsWith("0") && s2.length() > 1)){
            return false;
        }

        String res = s1+s2;
        while (res.length() <= num.length()){
            if (res.length() == num.length()){
                return res.equals(num);
            }

            String sum = addNum(s1, s2);
            s1 = s2;
            s2 = sum;
            res += sum;
        }

        return false;
    }

    private static String addNum(String s1, String s2){
        String sl = s1.length() > s2.length() ? s1 : s2;
        String ss = s1.length() <= s2.length() ? s1 : s2;

        int carry = 0;
        String res = "";
        for (int i = ss.length() - 1; i >= 0; i--){
            int n1 = sl.charAt(i+sl.length()-ss.length()) - '0';
            int n2 = ss.charAt(i) - '0';
            res = ((n1 + n2 + carry) % 10) + res;
            carry = (n1 +n2 + carry) / 10;
        }

        for (int i = sl.length() - ss.length() - 1; i >= 0; i--){
            int n1 = sl.charAt(i) - '0';
            res = ((n1 + carry)%10) + res;
            carry = (n1 + carry) / 10;
        }

        if (carry > 0 ){
            res = "1" + res;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(isAdditiveNumber1("112358"));
        System.out.println(isAdditiveNumber1("199100199"));
        System.out.println(isAdditiveNumber1("198019823962"));

        System.out.println(isAdditiveNumber2("112358"));
        System.out.println(isAdditiveNumber2("199100199"));
        System.out.println(isAdditiveNumber2("198019823962"));
        System.out.println(isAdditiveNumber1("11111111111111111111111111111111113"));
//        System.out.println(Long.MAX_VALUE);
    }
}
