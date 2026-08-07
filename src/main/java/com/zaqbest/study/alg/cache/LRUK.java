package com.zaqbest.study.alg.cache;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU-K算法
 *
 * 算法实现：https://www.gplvip.com/2019/05/01/LRU/
 */
public class LRUK<K, V> {

    private static final float hashLoadFactory = 0.75f;
    private ArrayList<History> histories;
    private LinkedHashMap<K, V> map;
    private int cacheSize, historyLength;
    private final int K_COUNT = 2; //进入缓存的计数要求

    public LRUK(int historyLength, int cacheSize) {
        this.historyLength = historyLength;
        this.cacheSize = cacheSize;
        histories = new ArrayList<>(historyLength);
        int capacity = (int) Math.ceil(cacheSize / hashLoadFactory) + 1;
        map = new LinkedHashMap(capacity, hashLoadFactory, true) {
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > LRUK.this.cacheSize;
            }
        };
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized boolean moveToCache(K key) {
        int hashCode = key.hashCode();
        if (inHistory(hashCode)) {
            return modifyHistory(hashCode);
        } else {
            History history = new History();
            history.setHash(hashCode);
            history.setTimes(1);

            histories.add(history);
            return false;
        }
    }

    private boolean modifyHistory(int objectHash) {
        for (History item : histories) {
            if (item.getHash() != objectHash) {
                continue;
            }

            if (item.getTimes() + 1 < K_COUNT) {
                item.setTimes(item.getTimes() + 1);
                histories.remove(item);
                histories.add(item);
                return false;
            }
            histories.remove(item);
            return true;
        }
        return false;
    }

    private boolean inHistory(int objectHash) {
        for (History item : histories) {
            if (item.getHash() == objectHash) {
                return true;
            }
        }
        return false;
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    private void sortOutHistory() {
        histories.remove(0);
    }

    public synchronized void clear() {
        map.clear();
    }

    public synchronized int usedSize() {
        return map.size();
    }

    public void print() {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.print(entry.getValue() + "--");
        }
        System.out.println();
    }

    class History {
        private int hash; // 资源Hash值

        private int times; // 使用次数

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

    }

}

