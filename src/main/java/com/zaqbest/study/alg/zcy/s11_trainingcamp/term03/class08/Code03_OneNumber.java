package com.zaqbest.study.alg.zcy.s11_trainingcamp.term03.class08;

/**
 * 给定一个正数N，表示你在纸上写下1~N所有的数字
 *
 * 返回在书写的过程中，一共写下了多少个1
 *
 * 数位DP问题，考察概率极小
 */
public class Code03_OneNumber {

	public static int solution1(int num) {
		if (num < 1) {
			return 0;
		}
		int count = 0;
		for (int i = 1; i != num + 1; i++) {
			count += get1Nums(i);
		}
		return count;
	}

	public static int get1Nums(int num) {
		int res = 0;
		while (num != 0) {
			if (num % 10 == 1) {
				res++;
			}
			num /= 10;
		}
		return res;
	}

	public static int solution2(int num) {
		if (num < 1) {
			return 0;
		}
		// num = 13626
		// len = 5位数
		int len = getLenOfNum(num);
		if (len == 1) {
			return 1;
		}
		//tmp1 = 10000
		int tmp1 = powerBaseOf10(len - 1);
		//num最高位first=1
		int first = num / tmp1;
		//最高位是1的个数
		int firstOneNum = first == 1 ? num % tmp1 + 1 : tmp1;
		//其他为是1的个数
		int otherOneNum = first * (len - 1) * (tmp1 / 10);
		return firstOneNum + otherOneNum + solution2(num % tmp1);
	}

	public static int getLenOfNum(int num) {
		int len = 0;
		while (num != 0) {
			len++;
			num /= 10;
		}
		return len;
	}

	public static int powerBaseOf10(int base) {
		return (int) Math.pow(10, base);
	}

	public static void main(String[] args) {
		int num = 50000000;
		long start1 = System.currentTimeMillis();
		System.out.println(solution1(num));
		long end1 = System.currentTimeMillis();
		System.out.println("cost time: " + (end1 - start1) + " ms");

		long start2 = System.currentTimeMillis();
		System.out.println(solution2(num));
		long end2 = System.currentTimeMillis();
		System.out.println("cost time: " + (end2 - start2) + " ms");

	}
}
