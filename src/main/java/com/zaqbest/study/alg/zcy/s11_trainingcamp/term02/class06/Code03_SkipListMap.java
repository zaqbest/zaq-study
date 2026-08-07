package com.zaqbest.study.alg.zcy.s11_trainingcamp.term02.class06;

import java.util.ArrayList;

/**
 * 跳表经典实现（不设置最大层数）
 */
public class Code03_SkipListMap {

	// 跳表的节点定义
	public static class SkipListNode<K extends Comparable<K>, V> {
		public K key;
		public V val;
		// 0  nextNodes.get(0)
		// 1  nextNodes.get(1)
		// i  nextNodes.get(i)
		// nextNodes.size()
		public ArrayList<SkipListNode<K, V>> nextNodes;

		public SkipListNode(K k, V v) {
			key = k;
			val = v;
			// node  7层指针
			// nextNodes.add(null)
			// nextNodes.add(null)
			// nextNodes.add(null)
			// nextNodes.add(null)
			// nextNodes.add(null)
			// nextNodes.add(null)
			// nextNodes.add(null)
			nextNodes = new ArrayList<SkipListNode<K, V>>();
		}

		// 遍历的时候，如果是往右遍历到的null(next == null), 遍历结束
		// 头(null), 头节点的null，认为最小
		// node  -> 头，node(null, "")  node.isKeyLess(!null)  true
		// node里面的key是否比otherKey小，true，不是false

		
		
		public boolean isKeyLess(K otherKey) {
			//  otherKey == null -> false 
			return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
		}

		public boolean isKeyEqual(K otherKey) {
			return (key == null && otherKey == null)
					|| (key != null && otherKey != null && key.compareTo(otherKey) == 0);
		}

	}

	public static class SkipListMap<K extends Comparable<K>, V> {
		private static final double PROBABILITY = 0.5; // < 0.5 继续做，>=0.5 停
		private SkipListNode<K, V> head;
		private int size;
		private int maxLevel;

		public SkipListMap() {
			head = new SkipListNode<K, V>(null, null);
			head.nextNodes.add(null);
			size = 0;
			maxLevel = 0;
		}

		// 从最高层开始，一路找下去，
		// 最终，找到第0层的<key的最右的节点
		private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
			if (key == null) {
				return null;
			}
			int level = maxLevel;
			SkipListNode<K, V> cur = head;
			while (level >= 0) { // 从上层跳下层
				//  cur  level  -> level-1
				cur = mostRightLessNodeInLevel(key, cur, level--);
			}
			return cur;
		}

		// 在level层里，如何往右移动
		// 现在来到的节点是cur，来到了cur的level层，在level层上，找到<key最后一个节点并返回
		private SkipListNode<K, V> mostRightLessNodeInLevel(K key, 
				SkipListNode<K, V> cur, 
				int level) {
			SkipListNode<K, V> next = cur.nextNodes.get(level);
			while (next != null && next.isKeyLess(key)) {
				cur = next;
				next = cur.nextNodes.get(level);
			}
			return cur;
		}

		/**
		 * 是否包含指定的key
		 *
		 * @param key
		 * @return
		 */
		public boolean containsKey(K key) {
			if (key == null) {
				return false;
			}
			SkipListNode<K, V> less = mostRightLessNodeInTree(key);
			SkipListNode<K, V> next = less.nextNodes.get(0);
			return next != null && next.isKeyEqual(key);
		}

		/**
		 * 新增一个key
		 *
		 * @param key
		 * @param value
		 */
		public void put(K key, V value) {
			if (key == null) {
				return;
			}
			SkipListNode<K, V> less = mostRightLessNodeInTree(key);
			SkipListNode<K, V> find = less.nextNodes.get(0);
			//如果key已经存在，更新值即可
			if (find != null && find.isKeyEqual(key)) {
				find.val = value;
			} else {
				size++;
				//至少有一个节点
				int newNodeLevel = 0;
				while (Math.random() < PROBABILITY) {
					newNodeLevel++;
				}
				//更新头结点的层数
				while (newNodeLevel > maxLevel) {
					head.nextNodes.add(null);
					maxLevel++;
				}
				SkipListNode<K, V> newNode = new SkipListNode<K, V>(key, value);
				//创建新节点的层数
				for (int i = 0; i <= newNodeLevel; i++) {
					newNode.nextNodes.add(null);
				}
				int level = maxLevel;
				SkipListNode<K, V> pre = head;
				//将新增的节点插入到跳表中
				while (level >= 0) {
					pre = mostRightLessNodeInLevel(key, pre, level);
					if (level <= newNodeLevel) {
						newNode.nextNodes.set(level, pre.nextNodes.get(level));
						pre.nextNodes.set(level, newNode);
					}
					level--;
				}
			}
		}

		/**
		 * 查找一个key
		 *
		 * @param key
		 * @return
		 */
		public V get(K key) {
			if (key == null) {
				return null;
			}
			SkipListNode<K, V> less = mostRightLessNodeInTree(key);
			SkipListNode<K, V> next = less.nextNodes.get(0);
			return next != null && next.isKeyEqual(key) ? next.val : null;
		}

		/**
		 * 删除
		 *
		 * @param key
		 */
		public void remove(K key) {
			if (containsKey(key)) {
				size--;
				int level = maxLevel;
				SkipListNode<K, V> pre = head;
				while (level >= 0) {
					pre = mostRightLessNodeInLevel(key, pre, level);
					SkipListNode<K, V> next = pre.nextNodes.get(level);
					// 1）在这一层中，pre下一个就是key
					// 2）在这一层中，pre的下一个key是>要删除key
					if (next != null && next.isKeyEqual(key)) {
						// free delete node memory -> C++
						// level : pre -> next(key) -> ...
						pre.nextNodes.set(level, next.nextNodes.get(level));
					}
					// 在level层只有一个节点了，就是默认节点head
					if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
						head.nextNodes.remove(level);
						maxLevel--;
					}
					level--;
				}
			}
		}

		/**
		 * 获取第一个key
		 *
		 * @return
		 */
		public K firstKey() {
			return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
		}

		/**
		 * 获取最后一个key
		 *
		 * @return
		 */
		public K lastKey() {
			int level = maxLevel;
			SkipListNode<K, V> cur = head;
			while (level >= 0) {
				SkipListNode<K, V> next = cur.nextNodes.get(level);
				while (next != null) {
					cur = next;
					next = cur.nextNodes.get(level);
				}
				level--;
			}
			return cur.key;
		}

		/**
		 * 大于或者等于输入key的key
		 *
		 * @param key
		 * @return
		 */
		public K ceillingKey(K key) {
			if (key == null) {
				return null;
			}
			SkipListNode<K, V> less = mostRightLessNodeInTree(key);
			SkipListNode<K, V> next = less.nextNodes.get(0);
			return next != null ? next.key : null;
		}

		/**
		 * 小于或者等于输入key的key
		 *
		 * @param key
		 * @return
		 */
		public K floorKey(K key) {
			if (key == null) {
				return null;
			}
			SkipListNode<K, V> less = mostRightLessNodeInTree(key);
			SkipListNode<K, V> next = less.nextNodes.get(0);
			return next != null && next.isKeyEqual(key) ? next.key : less.key;
		}

		public int size() {
			return size;
		}

	}

	// for test
	public static void printAll(SkipListMap<String, String> obj) {
		for (int i = obj.maxLevel; i >= 0; i--) {
			System.out.print("Level " + i + " : ");
			SkipListNode<String, String> cur = obj.head;
			while (cur.nextNodes.get(i) != null) {
				SkipListNode<String, String> next = cur.nextNodes.get(i);
				System.out.print("(" + next.key + " , " + next.val + ") ");
				cur = next;
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		SkipListMap<String, String> test = new SkipListMap<>();
		printAll(test);
		System.out.println("======================");
		test.put("A", "10");
		printAll(test);
		System.out.println("======================");
		test.remove("A");
		printAll(test);
		System.out.println("======================");
		test.put("E", "E");
		test.put("B", "B");
		test.put("A", "A");
		test.put("F", "F");
		test.put("C", "C");
		test.put("D", "D");
		printAll(test);
		System.out.println("======================");
		System.out.println(test.containsKey("B"));
		System.out.println(test.containsKey("Z"));
		System.out.println(test.firstKey());
		System.out.println(test.lastKey());
		System.out.println(test.floorKey("D"));
		System.out.println(test.ceillingKey("D"));
		System.out.println("======================");
		test.remove("D");
		printAll(test);
		System.out.println("======================");
		System.out.println(test.floorKey("D"));
		System.out.println(test.ceillingKey("D"));

	}

}
