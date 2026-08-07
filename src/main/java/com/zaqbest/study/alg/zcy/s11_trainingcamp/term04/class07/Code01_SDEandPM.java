package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 项目有四个信息:
 * 1)哪个项目经理提的
 * 2)被项目经理润色出来的时间点
 * 3)项目优先级
 * 4)项目花费的时间
 *
 * 项目经理们可以提交项目给程序员们，程序员可以做这些项目。
 * 比如长度为4的数组[1, 3, 2, 2]，表示1号项目经理提的，被项目经理润色出来的时间点是3，优先级2，花费程序员2个时间。
 * 所以给一个N*4的矩阵，就可以代表N个项目。
 *
 * 给定一个正数pm，表示项目经理的数量，每个项目经理只负责自己的那些项目，并且一次只能提交一个项目给程序员们，这个提交的项目做完了，才能再次提交。
 * 1) 经理对项目越喜欢，就会越早提交。
 * 2 ) 一个项目优先级越高越被喜欢;
 * 3) 如果优先级一样，花费时间越少越喜欢;
 * 4) 如果还一样，被项目经理润色出来的时间点越早越喜欢。
 *
 * 给定一个正数sde(software development enginee)，表示程序员的数量，所有经理提交了的项目，程序员会选自己喜欢的项目做，每个人做 完了一个项目，然后才会再来挑选。
 * 当程序员在挑选项目时，有自己的喜欢标准。
 * 1) 一个项目花费时间越少越被喜欢;
 * 2) 如果花费时间一样，该项目的负责人编号越小越被喜欢。
 *
 * 返回一个长度为N的数组，表示N个项目的结束时间。
 * 比如:
 * int pms = 2;
 * int sde = 2; //software development enginee
 * int[][] programs = { { 1, 1, 1, 2 }, { 1, 2, 1, 1 }, { 1, 3, 2, 2 }, { 2, 1, 1, 2 }, { 2, 3, 5, 5 } };
 * 返回:{3, 4, 5, 3, 9}
 */
public class Code01_SDEandPM {

	public static class Program {
		public int index; //项目的输入序号,0,1,2,3,...
		public int pm; //项目经理编号
		public int start; //开始时间
		public int rank; //优先级
		public int cost; //花费的时间

		public Program(int index, int pmNum, int begin, int rank, int cost) {
			this.index = index;
			this.pm = pmNum;
			this.start = begin;
			this.rank = rank;
			this.cost = cost;
		}
	}

	public static class PmLoveRule implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			if (o1.rank != o2.rank) {
				return o1.rank - o2.rank;
			} else if (o1.cost != o2.cost) {
				return o1.cost - o2.cost;
			} else {
				return o1.start - o2.start;
			}
		}

	}

	// 大黑盒（最大大小为pmSize)
	// 每一个pm，有自己的堆(PmLoveRule)
	// 每一个pm的堆里有堆顶，所有的堆顶会再组成一个，程序员堆(程序员喜好)
	// void add(...)  项目  pop()
	public static class BigQueues {
		//  PriorityQueue<Program> pmQ = pmQueues.get(i);
		private List<PriorityQueue<Program>> pmQueues;
		// 程序员堆（一个，程序员共享池）
		private Program[] sdeHeap;
		// indexes[i] -> i号pm的堆顶项目，在sde堆中处在啥位置
		private int[] indexes; 
		private int heapsize; // 程序员堆的大小

		public BigQueues(int pmNum) {
			heapsize = 0;
			sdeHeap = new Program[pmNum];
			indexes = new int[pmNum + 1];
			for (int i = 0; i <= pmNum; i++) {
				indexes[i] = -1;
			}
			pmQueues = new ArrayList<>();
			// i  pmQueues.get(i)
			for (int i = 0; i <= pmNum; i++) {
				pmQueues.add(new PriorityQueue<Program>(new PmLoveRule()));
			}
		}

		// 当前是否有项目可以弹出被程序员做
		public boolean isEmpty() {
			return heapsize == 0;
		}

		// 某一个项目加入了，黑盒里
		public void add(Program program) {
			PriorityQueue<Program> pmHeap = pmQueues.get(program.pm);
			pmHeap.add(program);
			// 有可能当前的项目，成了此时pm最喜欢的项目，换堆顶，调整sde堆中的项目
			Program head = pmHeap.peek(); // 现在的堆顶
			// 之前pm在sde堆中的自己的堆顶，sde？
			int heapindex = indexes[head.pm];
			if (heapindex == -1) { // 之前没堆顶, 
				sdeHeap[heapsize] = head;
				indexes[head.pm] = heapsize;
				heapInsert(heapsize++);
			} else { // 加此时的program之前，我有老堆顶
				sdeHeap[heapindex] = head;
				heapInsert(heapindex);
				heapify(heapindex);
			}
		}

		// 程序员挑项目，返回挑选的项目
		public Program pop() {
			Program head = sdeHeap[0];
			PriorityQueue<Program> queue = pmQueues.get(head.pm);
			queue.poll();
			if (queue.isEmpty()) { // 此时的pm手上没有项目了
				swap(0, heapsize - 1);
				sdeHeap[--heapsize] = null;
				indexes[head.pm] = -1;
			} else {
				sdeHeap[0] = queue.peek();
			}
			heapify(0);
			return head;
		}

		/**
		 * 向上移动的过程
		 *
		 * @param index
		 */
		private void heapInsert(int index) {
			while (index != 0) {
				int parent = (index - 1) / 2;
				if (sdeLoveRule(sdeHeap[parent], sdeHeap[index]) > 0) {
					swap(parent, index);
					index = parent;
				} else {
					break;
				}
			}
		}

		/**
		 * 向下移动的过程
		 *
		 * @param index
		 */
		private void heapify(int index) {
			int left = index * 2 + 1;
			int right = index * 2 + 2;
			int best = index;
			while (left < heapsize) {
				if (sdeLoveRule(sdeHeap[left], sdeHeap[index]) < 0) {
					best = left;
				}
				//是否有右节点
				if (right < heapsize && sdeLoveRule(sdeHeap[right], sdeHeap[best]) < 0) {
					best = right;
				}
				//已经不需要再向下移动了
				if (best == index) {
					break;
				}
				swap(best, index);
				index = best;
				left = index * 2 + 1;
				right = index * 2 + 2;
			}
		}

		private void swap(int index1, int index2) {
			Program p1 = sdeHeap[index1];
			Program p2 = sdeHeap[index2];
			sdeHeap[index1] = p2;
			sdeHeap[index2] = p1;
			indexes[p1.pm] = index2;
			indexes[p2.pm] = index1;
		}

		private int sdeLoveRule(Program p1, Program p2) {
			if (p1.cost != p2.cost) {
				return p1.cost - p2.cost;
			} else {
				return p1.pm - p2.pm;
			}
		}

	}

	public static class StartRule implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.start - o2.start;
		}

	}

	public static int[] workFinish(int pms, int sdes, int[][] programs) {
		// 所有被锁住的项目（3，6，9）   time=0
		PriorityQueue<Program> startQueue  = new PriorityQueue<Program>(new StartRule());
		for (int i = 0; i < programs.length; i++) {
			Program program = new Program(
					i, programs[i][0], programs[i][1], programs[i][2], programs[i][3]);
			startQueue.add(program);
		}
		// 所有的项目，在最开始的时候，都在start堆中，被锁住
		//
		// 程序员队列
		PriorityQueue<Integer> wakeQueue = new PriorityQueue<Integer>();
		for (int i = 0; i < sdes; i++) {
			wakeQueue.add(1);
		}
		// add   pop   isEmpty
		BigQueues bigQueues = new BigQueues(pms);
		int finish = 0; // 目前完成项目的数量
		int[] ans = new int[programs.length];	
		while (finish != ans.length) { // 没有得到所有的答案就继续
			// 最早醒来的程序员的时间, 也是总的推进时间点
			int sdeWakeTime = wakeQueue.poll(); 
			while (!startQueue.isEmpty()) {
				if (startQueue.peek().start > sdeWakeTime) {
					break;
				}
				bigQueues.add(startQueue.poll());
			}
			// 
			if (bigQueues.isEmpty()) { // 当前时间点并无项目可做
				wakeQueue.add(startQueue.peek().start);
			} else { // 当前时间点有项目可做
				Program program = bigQueues.pop();
				ans[program.index] = sdeWakeTime + program.cost;
				wakeQueue.add(ans[program.index]);
				finish++;
			}
		}
		return ans;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	public static void main(String[] args) {
		int pms = 2;
		int sde = 2;
		int[][] programs = { { 1, 1, 1, 2 }, { 1, 2, 1, 1 }, { 1, 3, 2, 2 }, { 2, 1, 1, 2 }, { 2, 3, 5, 5 } };
		int[] ans = workFinish(pms, sde, programs);
		printArray(ans);
	}

}
