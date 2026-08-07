package com.zaqbest.study.alg.zcy.s20_basic2020.class32;

/**
 *	树状数组/二插索引树
 * https://husterxsp.github.io/2017/06/06/binary-indexed-tree
 * https://zh.wikipedia.org/wiki/%E6%A0%91%E7%8A%B6%E6%95%B0%E7%BB%84
 */
public class Code01_IndexTree {

	// 下标从1开始！
	public static class IndexTree {

		private int[] tree;
		private int N;

		// 0位置弃而不用！
		public IndexTree(int size) {
			N = size;
			tree = new int[N + 1];
		}

		// 1~index 累加和是多少？
		public int sum(int index) {
			int ret = 0;
			while (index > 0) {
				ret += tree[index];
				index -= index & -index;
			}
			return ret;
		}

		// index & -index : 提取出index最右侧的1出来
		// index :           0011001000
		// index & -index :  0000001000
		public void add(int index, int d) {
			while (index <= N) {
				tree[index] += d;
				index += index & -index;
			}
		}
	}

	public static class Right {
		private int[] nums;
		private int N;

		public Right(int size) {
			N = size + 1;
			nums = new int[N + 1];
		}

		public int sum(int index) {
			int ret = 0;
			for (int i = 1; i <= index; i++) {
				ret += nums[i];
			}
			return ret;
		}

		public void add(int index, int d) {
			nums[index] += d;
		}

	}

	public static void main(String[] args) {
		IndexTree indexTree = new IndexTree(5);
		indexTree.add(1,1);
		indexTree.add(2,2);
		indexTree.add(3,3);
		indexTree.add(4,4);
		indexTree.add(5,5);

		int res = indexTree.sum(3);
		System.out.println(res);
	}

	public static void main0(String[] args) {
		int N = 100;
		int V = 100;
		int testTime = 2000000;
		IndexTree tree = new IndexTree(N);
		Right test = new Right(N);
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int index = (int) (Math.random() * N) + 1;
			if (Math.random() <= 0.5) {
				int add = (int) (Math.random() * V);
				tree.add(index, add);
				test.add(index, add);
			} else {
				if (tree.sum(index) != test.sum(index)) {
					System.out.println("Oops!");
				}
			}
		}
		System.out.println("test finish");
	}

}
