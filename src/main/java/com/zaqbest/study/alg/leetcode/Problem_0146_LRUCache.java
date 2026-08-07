package com.zaqbest.study.alg.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 146：实现LRUCache算法
 */
public class Problem_0146_LRUCache {
    private float factor = 0.75f;
    private int capacity;
    private LinkedHashMap<Integer, Integer> cache;

    /**
     * 注意点：
     * - cache容量，需要考虑加载因子，默认0.75
     * - accessOrder: true为访问顺序， false为插入顺序
     * @param capacity
     */
    public Problem_0146_LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>((int) (Math.ceil(capacity/factor) + 1), factor, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {

        return Optional.ofNullable(cache.get(key)).orElse(-1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

    public static void main(String[] args) {
        Problem_0146_LRUCache lRUCacheProblem0146 = new Problem_0146_LRUCache(2);
        lRUCacheProblem0146.put(1, 1); // 缓存是 {1=1}
        lRUCacheProblem0146.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCacheProblem0146.get(1);    // 返回 1
        lRUCacheProblem0146.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCacheProblem0146.get(2);    // 返回 -1 (未找到)
        lRUCacheProblem0146.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCacheProblem0146.get(1);    // 返回 -1 (未找到)
        lRUCacheProblem0146.get(3);    // 返回 3
        lRUCacheProblem0146.get(4);    // 返回 4

    }
}
