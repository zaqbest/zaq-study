package com.zaqbest.study.alg.zcy.s11_trainingcamp.term05.class02;

/**
 * 给定一个路径数组 paths，表示一张图。paths[i]==j 代表城市 i 连向城市 j，如果 paths[i]==i，
 * 则表示 i 城市是首都，一张图里只会有一个首都且图中除首都指向自己之 外不会有环。
 * 例如， paths=[9,1,4,9,0,4,8,9,0,1]，
 * 由数组表示的图可以知道，城市 1 是首都，所以距离为 0，离首都距离为 1 的城市只有城 市 9，
 * 离首都距离为 2 的城市有城市 0、3 和 7，离首都距离为 3 的城市有城市 4 和 8，
 * 离首都 距离为 4 的城市有城市 2、5 和 6。所以距离为 0 的城市有 1 座，
 * 距离为 1 的 城市有 1 座，距离 为 2 的城市有 3 座，距离为 3 的城市有 2 座，
 * 距离为 4 的城市有3 座。那么统计数组为nums=[1,1,3,2,3,0,0,0,0,0]，
 * nums[i]==j 代表距离为 i 的城市有 j 座。
 *
 * 要求实现一个 void 类型的函 数，输入一个路径数组 paths，直接在原数组上调整，
 * 使之变为 nums 数组，即 paths=[9,1,4,9,0,4,8,9,0,1]经过这个函数处理后变成 [1,1,3,2,3,0,0,0,0,0]。
 *
 * 【要求】
 * 如果 paths 长度为 N，请达到时间复杂度为 O(N)，额外空间复杂度为 O(1)。
 */
public class Code01_PathsToNums {

	public static void pathsToNums(int[] paths) {
		if (paths == null || paths.length == 0) {
			return;
		}
		// citiesPath -> distancesArray
		pathsToDistans(paths);

		// distancesArray -> countsArray
		distansToNums(paths);
	}

	public static void pathsToDistans(int[] paths) {
		int cap = 0;//首都
		for (int start = 0; start != paths.length; start++) {
 			if (paths[start] == start) {
				cap = start;
			} else if (paths[start] > -1) {
				int curI = paths[start];
				paths[start] = -1;
				int preI = start;
				while (paths[curI] != curI) {
					if (paths[curI] > -1) {
						int nextI = paths[curI];
						paths[curI] = preI;
						preI = curI;
						curI = nextI;
					} else {
						break;
					}
				}
				int value = paths[curI] == curI ? 0 : paths[curI];
				while (paths[preI] != -1) {
					int lastPreI = paths[preI];
					paths[preI] = --value;
					curI = preI;
					preI = lastPreI;
				}
				paths[preI] = --value;
			}
		}
		paths[cap] = 0;
	}

	public static void distansToNums(int[] disArr) {
		for (int i = 0; i != disArr.length; i++) {
			int index = disArr[i]; // index 负数 , 首都的index是0
			if (index < 0) {
				disArr[i] = 0; // important
				while (true) {
					index = -index;
					if (disArr[index] > -1) {
						disArr[index]++;
						break;
					} else { // 含义没变过来，还需要继续跳
						int nextIndex = disArr[index];
						disArr[index] = 1;
						index = nextIndex;
					}
				}
			}
		}
		disArr[0] = 1;
   	}

	public static void printArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] paths = { 9, 1, 4, 9, 0, 4, 8, 9, 0, 1 };
		printArray(paths);
		pathsToNums(paths);
		printArray(paths);

	}

}
