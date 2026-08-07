package com.zaqbest.study.alg.zcy.s40_leetcode.top_interview.topinterviewquestions;

public class Problem_0171_ExcelSheetColumnNumber {

	// 这道题反过来也要会写
	public static int titleToNumber(String s) {
		char[] str = s.toCharArray();
		int ans = 0;
		for (int i = 0; i < str.length; i++) {
			ans = ans * 26 + (str[i] - 'A') + 1;
		}
		return ans;
	}

	public static String numberToTitle(int n){
		String ans = "";
		while (n > 0){
			int m = n % 26;
			if (m > 0) {
				ans = (char) (m - 1 + 'A') + ans;
			}
			n /= 26;
		}

		return ans;
	}

	public static void main(String[] args) {
		System.out.println(numberToTitle(titleToNumber("FXSHRXW")));
	}
}
