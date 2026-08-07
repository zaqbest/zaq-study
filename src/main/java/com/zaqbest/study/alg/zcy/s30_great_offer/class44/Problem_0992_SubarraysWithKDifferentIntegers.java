package com.zaqbest.study.alg.zcy.s30_great_offer.class44;

import java.util.HashMap;
import java.util.Map;

public class Problem_0992_SubarraysWithKDifferentIntegers {

	// nums 数组，题目规定，nums中的数字，不会超过nums的长度
	// [ ]长度为5，0~5
	public static int subarraysWithKDistinct1(int[] nums, int k) {
		int n = nums.length;
		// k-1种数的窗口词频统计
		int[] lessCounts = new int[n + 1];
		// k种数的窗口词频统计
		int[] equalCounts = new int[n + 1];
		int lessLeft = 0;
		int equalLeft = 0;
		int lessKinds = 0; //k-1种类
		int equalKinds = 0;//k的种类
		int ans = 0;
		for (int r = 0; r < n; r++) {
			// 当前刚来到r位置！
			if (lessCounts[nums[r]] == 0) {
				lessKinds++;
			}
			if (equalCounts[nums[r]] == 0) {
				equalKinds++;
			}
			lessCounts[nums[r]]++;
			equalCounts[nums[r]]++;
			while (lessKinds == k) {
				if (lessCounts[nums[lessLeft]] == 1) {
					lessKinds--;
				}
				lessCounts[nums[lessLeft++]]--;
			}
			while (equalKinds > k) {
				if (equalCounts[nums[equalLeft]] == 1) {
					equalKinds--;
				}
				equalCounts[nums[equalLeft++]]--;
			}
			ans += lessLeft - equalLeft;
		}
		return ans;
	}

	public static int subarraysWithKDistinct2(int[] arr, int k) {
		return numsMostK(arr, k) - numsMostK(arr, k - 1);
	}

	public static int numsMostK(int[] arr, int k) {
		int i = 0, res = 0;
		HashMap<Integer, Integer> count = new HashMap<>();
		for (int j = 0; j < arr.length; ++j) {
			//新发现一种字符
			if (count.getOrDefault(arr[j], 0) == 0) {
				k--;
			}
			//次数加1
			count.put(arr[j], count.getOrDefault(arr[j], 0) + 1);
			//已经发现的字符，超过了k种
			while (k < 0) {
				//字符次数减1
				count.put(arr[i], count.get(arr[i]) - 1);
				//字符次数减到了0，k增加
				if (count.get(arr[i]) == 0) {
					k++;
				}
				//左指针向右移动
				i++;
			}
			//收集答案
			res += j - i + 1;
		}
		return res;
	}


	public static int numsMostK_Study1(int[] arr, int k) {
		int i=0, j=0, res = 0;
		Map<Integer, Integer> count = new HashMap<>();

		for (j = 0; j < arr.length; j++){
			if (count.getOrDefault(arr[j], 0) == 0){
				k--;
			}
			count.put(arr[j], count.getOrDefault(arr[j], 0) + 1);

			while (k < 0){
				count.put(arr[i], count.get(arr[i]) -1);
				if (count.get(arr[i]) == 0){
					k++;
				}
				i++;
			}

			res += j-i+1;
		}

		return res;
	}

}
