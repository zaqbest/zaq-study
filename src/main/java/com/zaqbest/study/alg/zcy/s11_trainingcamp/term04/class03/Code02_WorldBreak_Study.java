package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class03;

import java.util.HashSet;

/**
 * 假设所有字符都是小写字母.   长字符串是str
 * arr是去重的单词表, 每个单词都不是空字符串且可以使用任意次
 * 使用arr中的单词有多少种拼接str的方式，返回方法数.
 *
 * 思路：前缀树， dp
 */
public class Code02_WorldBreak_Study {

	/**
	 * 集合解法
	 *
	 * @param str
	 * @param arr
	 * @return
	 */
	public static int ways1_set(String str, String[] arr) {
		if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
			return 0;
		}

		HashSet<String> set = new HashSet<>();
		for (String item: arr){
			set.add(item);
		}

		return ways1_process(str, 0, set);
	}

	private static int ways1_process(String str, int i,  HashSet<String> set){
		if (i == str.length()){
			return 1;
		}

		int ways = 0;
		for (int end = i; end < str.length(); end++){
			String preStr = str.substring(i, end+1);
			if (set.contains(preStr)){
				ways += ways1_process(str, end + 1, set);
			}
		}

		return ways;
	}


	public static int ways2_set_dp(String str, String[] arr){
		HashSet<String> set = new HashSet<>();
		for (String item: arr){
			set.add(item);
		}
		int N = str.length();

		int[] dp = new int[N+1];
		dp[N] = 1;

		for (int i = N - 1; i >= 0; i--){
			for (int end = i; end < N; end++) {
				if (set.contains(str.substring(i, end+1))){
					dp[i] += dp[end+1]; //下一个位置从end+1开始
				}
			}
		}

		return dp[0];
	}

	static class Node {
		public Node[] nexts;
		public boolean end;

		public Node(){
			nexts = new Node[26];
			end = false;
		}
	}
	public static int ways2_tirenode(String str, String[] arr) {
		if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
			return 0;
		}

		Node root = new Node();
		for (String s: arr){
			char[] chs = s.toCharArray();
			Node node = root;
			int index = 0;
			for (char c: chs){
				index = c - 'a';
				if (node.nexts[index] == null){
					node.nexts[index] = new Node();
				}
				node = node.nexts[index];
			}

			node.end = true;
		}

		return ways2_tirenode_helper(str.toCharArray(), root, 0);
	}

	//str[i...]被分解的方案数
	private static int ways2_tirenode_helper(char[] str, Node root, int i) {
		if (i == str.length)
			return 1;

		int ways = 0;
		Node cur = root;
		int N = str.length;

		for (int end = i; end < N; end++){
			int path = str[end];
			if (cur.nexts[path] == null){
				break;
			}

			cur = cur.nexts[path];
			if (cur.end){
				ways += ways2_tirenode_helper(str, cur, end+ 1);
			}
		}

		return ways;

	}

}
