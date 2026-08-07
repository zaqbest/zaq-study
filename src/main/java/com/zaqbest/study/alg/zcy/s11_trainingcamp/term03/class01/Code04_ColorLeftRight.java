package com.zaqbest.study.alg.zcy.s11_trainingcamp.term03.class01;

/**
 * 有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将 会被覆盖。目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。 返回最少需要涂染几个正方形。
 * 如样例所示: s = RGRGR 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
 */
public class Code04_ColorLeftRight {

	// RGRGR -> RRRGG
	public static int minPaint(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] str = s.toCharArray();
		int[] right = new int[str.length];
		//right[i]指的是right[i...N-1]包含多少个R
		right[str.length - 1] = str[str.length - 1] == 'R' ? 1 : 0;
		for (int i = str.length - 2; i >= 0; i--) {
			right[i] = right[i + 1] + (str[i] == 'R' ? 1 : 0);
		}
		int res = right[0];//理解为分割线在最左侧，则需要包所有的R都变成G；左侧无，右侧0...N-1
		int left = 0;//左侧总共有多少个G
		for (int i = 0; i < str.length - 1; i++) {
			left += str[i] == 'G' ? 1 : 0;
			res = Math.min(res, left + right[i + 1]);
		}
		//最后一种情况，0...N-1都左侧，右侧是无
		res = Math.min(res, left + (str[str.length - 1] == 'G' ? 1 : 0));
		return res;
	}

	public static void main(String[] args) {
		String test = "GGGGGR";
		System.out.println(minPaint(test));

	}

}
