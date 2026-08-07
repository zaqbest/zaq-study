package com.zaqbest.study.alg.zcy.s10_primary.class02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2）如何用队列结构实现栈结构
 */
public class Code07_TwoQueueImplementStack {

	public static class TwoQueueStack<T> {
		public Queue<T> queue;
		public Queue<T> help;

		public TwoQueueStack() {
			queue = new LinkedList<>();
			help = new LinkedList<>();
		}

		public void push(T value) {
			queue.offer(value);
		}

		public T poll() {
			while (queue.size() > 1) {
				help.offer(queue.poll());
			}
			T ans = queue.poll();
			Queue<T> tmp = queue;
			queue = help;
			help = tmp;
			return ans;
		}

		public T peek() {
			while (queue.size() > 1) {
				help.offer(queue.poll());
			}
			T ans = queue.peek();
			Queue<T> tmp = queue;
			queue = help;
			help = tmp;
			help.offer(ans);
			return ans;
		}

		public boolean isEmpty() {
			return queue.isEmpty();
		}

	}

}
