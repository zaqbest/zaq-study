package com.zaqbest.study.alg.zcy.s30_great_offer.class22;

//import cn.hutool.json.JSONUtil;

/**
 * 689. 三个无重叠子数组的最大和
 *
 * 本题测试链接 : https://leetcode.cn/problems/maximum-sum-of-3-non-overlapping-subarrays/
 *
 * 思路：
 * - 从左往右的尝试模型
 * 利用left和right数组，加速查查范围上的最大值
 */
public class Code01_MaximumSumof3NonOverlappingSubarrays {

	public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int N = nums.length;
		int[] range = new int[N];
		int[] left = new int[N];
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += nums[i];
		}
		range[0] = sum; //[i]表示i..i+k-1的和
		left[k - 1] = 0; //表示0~i的最大和在什么位置
		int max = sum;
		for (int i = k; i < N; i++) {
			sum = sum - nums[i - k] + nums[i];
			range[i - k + 1] = sum;
			left[i] = left[i - 1];
			if (sum > max) {
				max = sum;
				left[i] = i - k + 1;
			}
		}
		sum = 0;
		for (int i = N - 1; i >= N - k; i--) {
			sum += nums[i];
		}
		max = sum;
		int[] right = new int[N];
		right[N - k] = N - k;
		for (int i = N - k - 1; i >= 0; i--) {
			sum = sum - nums[i + k] + nums[i];
			right[i] = right[i + 1];
			if (sum >= max) {
				max = sum;
				right[i] = i;
			}
		}
		int a = 0;
		int b = 0;
		int c = 0;
		max = 0;
		for (int i = k; i < N - 2 * k + 1; i++) { // 中间一块的起始点 (0...k-1)选不了 i == N-1
			int part1 = range[left[i - 1]];
			int part2 = range[i];
			int part3 = range[right[i + k]];
			if (part1 + part2 + part3 > max) {
				max = part1 + part2 + part3;
				a = left[i - 1];
				b = i;
				c = right[i + k];
			}
		}
		return new int[] { a, b, c };
	}

	public static void main(String[] args) {
		int[] nums={1,2,1,2,6,7,5,1};
		int k = 2;

		int[] res = maxSumOfThreeSubarrays(nums, k);
//		System.out.println(JSONUtil.toJsonStr(res));
	}

}
