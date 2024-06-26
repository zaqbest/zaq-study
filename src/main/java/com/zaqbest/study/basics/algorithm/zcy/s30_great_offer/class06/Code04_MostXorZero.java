package com.zaqbest.study.basics.algorithm.zcy.s30_great_offer.class06;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 网易面试题
 *
 * 给定数组arr, 最大可以划分成多少个异或和为0的子数组？
 * 例如[0,1,2,3,0,1,2,3] 可以划分成 0 | 1,2,3 | 0 | 1，2,3
 * 最多划分成4个，结果返回4
 *
 * 思路
 * - 从左往右的尝试模型
 * - 划分可能性的方式（思路清奇）
 * - 假设答案法（超级难）
 */
public class Code04_MostXorZero {

	// 暴力方法
	public static int comparator(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[] eor = new int[N];
		eor[0] = arr[0];
		for (int i = 1; i < N; i++) {
			eor[i] = eor[i - 1] ^ arr[i];
		}
		return process(eor, 1, new ArrayList<>());
	}

	// index去决定：前一坨部分，结不结束！
	// 如果结束！就把index放入到parts里去
	// 如果不结束，就不放
	public static int process(int[] eor, int index, ArrayList<Integer> parts) {
		int ans = 0;
		if (index == eor.length) {
			parts.add(eor.length);
			ans = eorZeroParts(eor, parts);
			parts.remove(parts.size() - 1);
		} else {
			int p1 = process(eor, index + 1, parts);
			parts.add(index);
			int p2 = process(eor, index + 1, parts);
			parts.remove(parts.size() - 1);
			ans = Math.max(p1, p2);
		}
		return ans;
	}

	public static int eorZeroParts(int[] eor, ArrayList<Integer> parts) {
		int L = 0;
		int ans = 0;
		for (Integer end : parts) {
			if ((eor[end - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) {
				ans++;
			}
			L = end;
		}
		return ans;
	}

	// 时间复杂度O(N)的方法
	public static int mostXor(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[] dp = new int[N];
		
		// key 某一个前缀异或和
		// value 这个前缀异或和上次出现的位置(最晚！)
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		// 0~i整体的异或和
		int xor = 0;
		for (int i = 0; i < N; i++) {
			xor ^= arr[i];
			if (map.containsKey(xor)) { // 可能性2，最后一个部分异或和为0
				int pre = map.get(xor); //上一次出现此异或和的位置，如果找不到则为-1
				dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
			}
			if (i > 0) { //可能性1：最后一个部分异或和不是0
				dp[i] = Math.max(dp[i - 1], dp[i]);
			}
			map.put(xor, i);
		}
		return dp[N - 1];
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = {0,1,2,3,0,1,2,3};
		mostXor(arr);
	}

	// for test
	public static void main0(String[] args) {
		int testTime = 150000;
		int maxSize = 12;
		int maxValue = 5;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int res = mostXor(arr);
			int comp = comparator(arr);
			if (res != comp) {
				succeed = false;
				printArray(arr);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
