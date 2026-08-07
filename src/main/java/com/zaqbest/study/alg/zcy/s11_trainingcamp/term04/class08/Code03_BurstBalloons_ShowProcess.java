package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class08;

import java.util.HashMap;
import java.util.Map;

public class Code03_BurstBalloons_ShowProcess {

	public static int maxCoins1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0];
		}
		int N = arr.length;
		int[] help = new int[N + 2];
		help[0] = 1;
		help[N + 1] = 1;
		for (int i = 0; i < N; i++) {
			help[i + 1] = arr[i];
		}

		Map<String, Integer> resMap = new HashMap<>();
		int res =  process(help, 1, N, resMap);

		//逆序打印结果！！！！
		showProcess(help, resMap, 1, N);

		return res;
	}

	public static void showProcess(int[] help, Map<String, Integer> resMap, int L, int R){
		if (L > R){
			return;
		}

		int index = resMap.get(L+","+R);
		System.out.println(help[index]);
		if(index == L){
			showProcess(help, resMap, L+1, R);
		} else if (index == R){
			showProcess(help, resMap, L, R-1);
		} else {
			showProcess(help, resMap, L, index-1);
			showProcess(help, resMap,index+1, R);
		}
	}

	// 打爆arr[L..R]范围上的所有气球，返回最大的分数
	// 假设arr[L-1]和arr[R+1]一定没有被打爆
	public static int process(int[] arr, int L, int R, Map<String, Integer> resMap) {
		int lastIndex = -1;
		if (L == R) {// 如果arr[L..R]范围上只有一个气球，直接打爆即可
			resMap.put(L+","+R, L);
			return arr[L - 1] * arr[L] * arr[R + 1];
		}
		// 最后打爆arr[L]的方案，和最后打爆arr[R]的方案，先比较一下
//		int max = Math.max(
//				arr[L - 1] * arr[L] * arr[R + 1] + process(arr, L + 1, R, resMap),
//				arr[L - 1] * arr[R] * arr[R + 1] + process(arr, L, R - 1, resMap));
		int lastL = arr[L - 1] * arr[L] * arr[R + 1] + process(arr, L + 1, R, resMap);
		int lastR = arr[L - 1] * arr[R] * arr[R + 1] + process(arr, L, R - 1, resMap);
		if (lastL > lastR){
			lastIndex = L;
		} else {
			lastIndex = R;
		}
		int max = Math.max(lastL, lastR);
		// 尝试中间位置的气球最后被打爆的每一种方案
		for (int i = L + 1; i < R; i++) {
//			max = Math.max(max,
//					arr[L - 1] * arr[i] * arr[R + 1] + process(arr, L, i - 1, resMap)
//							+ process(arr, i + 1, R, resMap));
			int lastI = arr[L - 1] * arr[i] * arr[R + 1] + process(arr, L, i - 1, resMap)
					+ process(arr, i + 1, R, resMap);
			if (lastI > max){
				lastIndex = i;
				max = lastI;
			}
		}

		resMap.put(L+","+R, lastIndex);
		return max;
	}


	public static void main(String[] args) {
		int[] arr = { 2,5,7,3};
		System.out.println(maxCoins1(arr));
	}

}
