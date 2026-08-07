package com.zaqbest.study.alg.zcy.s30_great_offer.class42;

/**
 * 335. 路径交叉
 *
 *
 * https://leetcode.cn/problems/self-crossing/
 */
public class Problem_0335_SelfCrossing {

	public static boolean isSelfCrossing(int[] x) {
		//只有3次移动的情况，不可能相交
		//情况1：不足4条线的情况，肯定不会交汇
		if (x == null || x.length < 4) {
			return false;
		}
		//情况2：考虑超过4条线，和超过5条线的情况，参考图片2.1和2.2
		if ((x.length > 3 && x[2] <= x[0] && x[3] >= x[1])
				|| (x.length > 4 
						&& ((x[3] <= x[1] && x[4] >= x[2]) || (x[3] == x[1] && x[0] + x[4] >= x[2])))) {
			return true;
		}
		//情况3：大于等于6，参考图片3.1和3.2
		for (int i = 5; i < x.length; i++) {
			if (x[i - 1] <= x[i - 3] && ((x[i] >= x[i - 2])
					|| (x[i - 2] >= x[i - 4] && x[i - 5] + x[i - 1] >= x[i - 3] && x[i - 4] + x[i] >= x[i - 2]))) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 2, 3, 2, 2 };
		System.out.println(isSelfCrossing(arr));
	}

}
