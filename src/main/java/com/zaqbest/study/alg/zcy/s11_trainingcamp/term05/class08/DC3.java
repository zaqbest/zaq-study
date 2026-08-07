package com.zaqbest.study.alg.zcy.s11_trainingcamp.term05.class08;

//import cn.hutool.json.JSONUtil;

/**
 * DC3算法求后缀数组
 *
 * 示例：
 * 单词：mississippi
 * sa=[10, 7, 4, 1, 0, 9, 8, 6, 3, 5, 2]
 * rank=[5, 4, 11, 9, 3, 10, 8, 2, 7, 6, 1]
 *
 * sa的含义
 * 10 指的是str[10..],排名第0位
 * 7  指的是str[7..], 排名第1位
 *
 * rank的含义
 * 5 指的是str[0..], 排名第5位
 */
public class DC3 {

	public int[] sa;//后缀数组，Suffix Arra

	public int[] rank;//名次数组

	/**
	 * 主调用方法
	 *
	 * 调用约定
	 * - nums中的数字必须大于0
	 * - max为数组中最大的数
	 *
	 * @param nums
	 * @param max
	 */
	public DC3(int[] nums, int max) {
		sa = sa(nums, max);
		rank = rank();
	}

	private int[] sa(int[] nums, int max) {
		int n = nums.length;
		int[] arr = new int[n + 3];
		for (int i = 0; i < n; i++) {
			arr[i] = nums[i];
		}
		return skew(arr, n, max);
	}

	private int[] skew(int[] nums, int n, int K) {
		//n0指的是0,3,6总共有多少个数
		int n0 = (n + 2) / 3, n1 = (n + 1) / 3, n2 = n / 3, n02 = n0 + n2;
		//s12内容为[1,2,4,5,7,8,10]
		int[] s12 = new int[n02 + 3], sa12 = new int[n02 + 3];
		for (int i = 0, j = 0; i < n + (n0 - n1); ++i) {
			if (0 != i % 3) {
				s12[j++] = i;
			}
		}
		radixPass(nums, s12, sa12, 2, n02, K);
		radixPass(nums, sa12, s12, 1, n02, K);
		radixPass(nums, s12, sa12, 0, n02, K);
		int name = 0, c0 = -1, c1 = -1, c2 = -1;
		for (int i = 0; i < n02; ++i) {
			if (c0 != nums[sa12[i]] || c1 != nums[sa12[i] + 1] || c2 != nums[sa12[i] + 2]) {
				name++;
				c0 = nums[sa12[i]];
				c1 = nums[sa12[i] + 1];
				c2 = nums[sa12[i] + 2];
			}
			if (1 == sa12[i] % 3) {
				s12[sa12[i] / 3] = name;
			} else {
				s12[sa12[i] / 3 + n0] = name;
			}
		}
		if (name < n02) {
			sa12 = skew(s12, n02, name);
			for (int i = 0; i < n02; i++) {
				s12[sa12[i]] = i + 1;
			}
		} else {
			for (int i = 0; i < n02; i++) {
				sa12[s12[i] - 1] = i;
			}
		}
		int[] s0 = new int[n0], sa0 = new int[n0];
		for (int i = 0, j = 0; i < n02; i++) {
			if (sa12[i] < n0) {
				s0[j++] = 3 * sa12[i];
			}
		}
		radixPass(nums, s0, sa0, 0, n0, K);
		int[] sa = new int[n];
		for (int p = 0, t = n0 - n1, k = 0; k < n; k++) {
			int i = sa12[t] < n0 ? sa12[t] * 3 + 1 : (sa12[t] - n0) * 3 + 2;
			int j = sa0[p];
			if (sa12[t] < n0 ? leq(nums[i], s12[sa12[t] + n0], nums[j], s12[j / 3])
					: leq(nums[i], nums[i + 1], s12[sa12[t] - n0 + 1], nums[j], nums[j + 1], s12[j / 3 + n0])) {
				sa[k] = i;
				t++;
				if (t == n02) {
					for (k++; p < n0; p++, k++) {
						sa[k] = sa0[p];
					}
				}
			} else {
				sa[k] = j;
				p++;
				if (p == n0) {
					for (k++; t < n02; t++, k++) {
						sa[k] = sa12[t] < n0 ? sa12[t] * 3 + 1 : (sa12[t] - n0) * 3 + 2;
					}
				}
			}
		}
		return sa;
	}

	private void radixPass(int[] nums, int[] input, int[] output, int offset, int n, int k) {
		int[] cnt = new int[k + 1];
		for (int i = 0; i < n; ++i) {
			cnt[nums[input[i] + offset]]++;
		}
		for (int i = 0, sum = 0; i < cnt.length; ++i) {
			int t = cnt[i];
			cnt[i] = sum;
			sum += t;
		}
		for (int i = 0; i < n; ++i) {
			output[cnt[nums[input[i] + offset]]++] = input[i];
		}
	}

	private boolean leq(int a1, int a2, int b1, int b2) {
		return a1 < b1 || (a1 == b1 && a2 <= b2);
	}

	private boolean leq(int a1, int a2, int a3, int b1, int b2, int b3) {
		return a1 < b1 || (a1 == b1 && leq(a2, a3, b2, b3));
	}

	private int[] rank() {
		int n = sa.length;
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[sa[i]] = i + 1;
		}
		return ans;
	}

	public static void main(String[] args) {
		String s = "mississippi";
		int[] nums = new int[s.length()];
		int max = 0;
		for (int i = 0, j = 0; i < s.length(); i++){
			nums[j++] = s.charAt(i);
			max = Math.max(max, s.charAt(i));
		}

		DC3 dc3 = new DC3(nums, max);
//		System.out.println(JSONUtil.toJsonStr(dc3.sa));
//		System.out.println(JSONUtil.toJsonStr(dc3.rank));
	}
}