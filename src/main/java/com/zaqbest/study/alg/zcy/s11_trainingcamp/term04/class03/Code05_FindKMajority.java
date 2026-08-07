package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class03;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 * 1)给定一个数组arr，如果有某个数出现次数超过了数组长度的一半，打印这个数，如果没有不打印
 *
 * 2)给定一个数组arr和整数k，arr长度为N，如果有某些数出现次数超过了N/K，打印这些数，如果没有不打印
 */
public class Code05_FindKMajority {

	public static void printHalfMajor(int[] arr) {
		int cand = 0;//候选人，candidate
		int HP = 0; //血量，不小于0；大于0是，cand有效
		for (int i = 0; i < arr.length; i++) {
			if (HP == 0) {
				cand = arr[i];
				HP = 1;
			} else if (arr[i] == cand) {
				HP++;
			} else {
				HP--;
			}
		}
		if(HP == 0) {
			System.out.println("no such number.");
			return;
		}
		HP = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == cand) {
				HP++;
			}
		}
		if (HP > arr.length / 2) {
			System.out.println(cand);
		} else {
			System.out.println("no such number.");
		}
	}

	public static void printKMajor(int[] arr, int K) {
		if (K < 2) {
			System.out.println("the value of K is invalid.");
			return;
		}
		HashMap<Integer, Integer> cands = new HashMap<Integer, Integer>();
		for (int i = 0; i != arr.length; i++) {
			if (cands.containsKey(arr[i])) {
				cands.put(arr[i], cands.get(arr[i]) + 1);
			} else {
				if (cands.size() == K - 1) {
					allCandsMinusOne(cands);
				} else {
					cands.put(arr[i], 1);
				}
			}
		}
		HashMap<Integer, Integer> reals = getReals(arr, cands);
		boolean hasPrint = false;
		for (Entry<Integer, Integer> set : cands.entrySet()) {
			Integer key = set.getKey();
			if (reals.get(key) > arr.length / K) {
				hasPrint = true;
				System.out.print(key + " ");
			}
		}
		System.out.println(hasPrint ? "" : "no such number.");
	}

	public static void allCandsMinusOne(HashMap<Integer, Integer> map) {
		List<Integer> removeList = new LinkedList<Integer>();
		for (Entry<Integer, Integer> set : map.entrySet()) {
			Integer key = set.getKey();
			Integer value = set.getValue();
			if (value == 1) {
				removeList.add(key);
			}
			map.put(key, value - 1);
		}
		for (Integer removeKey : removeList) {
			map.remove(removeKey);
		}
	}

	public static HashMap<Integer, Integer> getReals(int[] arr,
			HashMap<Integer, Integer> cands) {
		HashMap<Integer, Integer> reals = new HashMap<Integer, Integer>();
		for (int i = 0; i != arr.length; i++) {
			int curNum = arr[i];
			if (cands.containsKey(curNum)) {
				if (reals.containsKey(curNum)) {
					reals.put(curNum, reals.get(curNum) + 1);
				} else {
					reals.put(curNum, 1);
				}
			}
		}
		return reals;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
		printHalfMajor(arr);
		int K = 4;
		printKMajor(arr, K);
	}

}
