package com.zaqbest.study.basics.algorithm.zcy.s11_trainingcamp.term03.class01;

/**
 * 给定一个正整数M，请构造出一个长度为M的数组arr，要求
 * 对任意的i、j、k三个位置，如果i<j<k，都有arr[i] + arr[k] != 2*arr[j]
 * 返回构造出的arr
 *
 * 思路：分治的思想;奇偶变换
 */
public class Code06_MakeNo {
	
	// 生成长度为size的达标数组
	// 达标：对于任意的 i<k<j，满足   [i] + [j]  != [k] * 2
	public static int[] makeNo(int size) {
		if (size == 1) {
			return new int[] { 1 };//任意数都可以，不一定要是1
		}
		// size
		// 一半长达标来
		// 7   :   4
		// 8   :   4
		// [4个奇数] [3个偶]
		int halfSize = (size + 1) / 2;
		int[] base = makeNo(halfSize);
		// base -> 等长奇数达标来
		// base -> 等长偶数达标来
		int[] ans = new int[size];
		int index = 0;
		//前半段用基数填充
		for(; index < halfSize;index++) {
			ans[index] = base[index] * 2 + 1;
		}
		//后半段用偶数填充
		for(int i = 0 ;index < size;index++,i++) {
			ans[index] = base[i] * 2; 
		}
		return ans;
	}
	

	// 检验函数
	public static boolean isValid(int[] arr) {
		int N = arr.length;
		for (int i = 0; i < N; i++) {
			for (int k = i + 1; k < N; k++) {
				for (int j = k + 1; j < N; j++) {
					
					
					if (arr[i] + arr[j] == 2 * arr[k]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		for (int N = 1; N < 1000; N++) {
			int[] arr = makeNo(N);
			if (!isValid(arr)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
		
		System.out.println(isValid(makeNo(1042)));
		System.out.println(isValid(makeNo(2981)));
		

	}

}
