package com.zaqbest.study.alg.zcy.s10_primary.class09;

import java.util.HashSet;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Code02_Light {

	public static int minLight1(String road) {
		if (road == null || road.length() == 0) {
			return 0;
		}
		return process(road.toCharArray(), 0, new HashSet<>());
	}

	/**
	 *
	 * @param str
	 * @param index 当前处理的索引index
	 * @param lights 被点亮灯的集合
	 * @return 被点亮灯的个数
	 */
	public static int process(char[] str, int index, HashSet<Integer> lights) {
		if (index == str.length) {
			for (int i = 0; i < str.length; i++) {
				if (str[i] != 'X') {
					if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
						return Integer.MAX_VALUE;
					}
				}
			}
			return lights.size();
		} else {
			//该位置不点亮
			int no = process(str, index + 1, lights);
			int yes = Integer.MAX_VALUE;
			//如果是居民区，可以选择是否被点亮
			if (str[index] == '.') {
				lights.add(index);
				yes = process(str, index + 1, lights);
				//调用完成后，清理现场
				lights.remove(index);
			}
			return Math.min(no, yes);
		}
	}

	public static int minLight2(String road) {
		char[] str = road.toCharArray();
		int index = 0;
		int light = 0;
		while (index < str.length) {
			if (str[index] == 'X') {
				index++;
			} else {
				light++;
				if (index + 1 == str.length) { //.$的情况
					break;
				} else {
					if (str[index + 1] == 'X') { //.X的情况
						index = index + 2;
					} else { //..的情况   .,电亮，肯定被照亮，下一个需要处理的位置
						index = index + 3;
					}
				}
			}
		}
		return light;
	}

	// for test
	public static String randomString(int len) {
		char[] res = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Math.random() < 0.5 ? 'X' : '.';
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		int len = 20;
		int testTime = 100000;
		for (int i = 0; i < testTime; i++) {
			String test = randomString(len);
			int ans1 = minLight1(test);
			int ans2 = minLight2(test);
			if (ans1 != ans2) {
				System.out.println("oops!");
			}
		}
		System.out.println("finish!");
	}
}
