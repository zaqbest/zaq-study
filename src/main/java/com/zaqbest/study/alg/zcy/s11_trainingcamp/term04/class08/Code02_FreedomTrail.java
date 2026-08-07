package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class08;

//import cn.hutool.core.util.RandomUtil;
import com.zaqbest.study.alg.leetcode.Problem_0514_FindRotateSteps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 514. 自由之路
 *
 * {@link  Problem_0514_FindRotateSteps}
 */
public class Code02_FreedomTrail {

	public static int dial(int i1, int i2, int size) {
		return Math.min(Math.abs(i1 - i2), Math.min(i1, i2) + size - Math.max(i1, i2));
	}

	public static int dial2(int i1, int i2, int size) {

		int l = i1 < i2 ? i1: i2;
		int h = i1 >= i2 ? i1: i2;

		return Math.min(h-l, l+size-h);
	}

	public static int findRotateSteps1(String r, String k) {
		char[] ring = r.toCharArray();
		int rsize = ring.length;
		//每一个字符对应的位置列表
		HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
		for (int i = 0; i < rsize; i++) {
			if (!map.containsKey(ring[i])) {
				map.put(ring[i], new ArrayList<>());
			}
			map.get(ring[i]).add(i);
		}
		return minSteps1(0, 0, k.toCharArray(), map, rsize);
	}

	// preStrIndex : 目前被对齐的位置是什么位置
	// keyIndex : 请搞定key[keyIndex...]
	// key      : 目标串（固定参数）
	// map      : 任何一个字符，什么位置上有它（固定参数）
	// rsize    : 电话表盘上一共的位置个数（固定参数）
	// 返回最低代价
	public static int minSteps1(
			int preStrIndex,
			int keyIndex, 
			char[] key, 
			HashMap<Character, ArrayList<Integer>> map,
			int rsize) {
		if (keyIndex == key.length) {
			return 0;
		}
		int ans = Integer.MAX_VALUE;
		// key[keyIndex]
		//依次尝试该字符出现的每一个位置
		for (int curStrIndex : map.get(key[keyIndex])) {
			int step = dial(preStrIndex, curStrIndex, rsize) + 1
					+ minSteps1(curStrIndex, keyIndex + 1, key, map, rsize);
			ans = Math.min(ans, step);
		}
		return ans;
	}

	public static int findRotateSteps2(String r, String k) {
		char[] ring = r.toCharArray();
		int rsize = ring.length;
		HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
		for (int i = 0; i < rsize; i++) {
			if (!map.containsKey(ring[i])) {
				map.put(ring[i], new ArrayList<>());
			}
			map.get(ring[i]).add(i);
		}
		int[][] dp = new int[rsize][k.length() + 1];
		for (int i = 0; i < rsize; i++) {
			for (int j = 0; j <= k.length(); j++) {
				dp[i][j] = -1;
			}
		}
		return minSteps2(0, 0, k.toCharArray(), map, rsize, dp);
	}

	public static int minSteps2(int preStrIndex, int keyIndex, char[] key, HashMap<Character, ArrayList<Integer>> map,
			int rsize, int[][] dp) {
		if (dp[preStrIndex][keyIndex] != -1) {
			return dp[preStrIndex][keyIndex];
		}
		if (keyIndex == key.length) {
			dp[preStrIndex][keyIndex] = 0;
			return 0;
		}
		int ans = Integer.MAX_VALUE;
		for (int curStrIndex : map.get(key[keyIndex])) {
			int step = dial(preStrIndex, curStrIndex, rsize) + 1
					+ minSteps2(curStrIndex, keyIndex + 1, key, map, rsize, dp);
			ans = Math.min(ans, step);
		}
		dp[preStrIndex][keyIndex] = ans;
		return ans;
	}

	// 彻底动态规划的方法请同学们自己改出


	public static void main(String[] args) {
		for(int i =0; i < 100;i++){
			int i1 = new Random().nextInt(0,100);
			int i2 =  new Random().nextInt(0,100);

			int r1 = dial(i1, i2, 100);
			int r2 = dial2(i1, i2, 100);

			if (r1 != r2){
				System.out.println("oops!");
				break;
			}
		}

		System.out.println("finished!");
	}
}
