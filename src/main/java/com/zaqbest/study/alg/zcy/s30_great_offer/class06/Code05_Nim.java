package com.zaqbest.study.alg.zcy.s30_great_offer.class06;

/**
 * 尼姆博弈
 *
 * 先手的人，一定要保证留给后手的说有数字异或和为0
 */
public class Code05_Nim {

	// 保证arr是正数数组
	public static void printWinner(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		if (eor == 0) {
			System.out.println("后手赢");
		} else {
			System.out.println("先手赢");
		}
	}

}
