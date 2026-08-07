package com.zaqbest.study.alg.mihoyo;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 有n个游戏，每个游戏需要1单位时间来完成。
 * 第i个游戏必须在t[i]时间或之前完成，完成则获得w[i]分，没完成则扣除w[i]
 *
 * 求最大获分
 *
 * <a>https://leetcode-cn.com/circle/discuss/ahSrqZ/</a>
 */
public class Problem_MaxPoint {


    static class Node{
        public int begin;
        public int point;

        public Node(int begin, int point) {
            this.begin = begin;
            this.point = point;
        }
    }

    public static int maxPoint(int[] t, int[] w){
        if (t == null || w == null || t.length != w.length){
            return 0;
        }

        //所有w[i]的累加和
        int pointAll = 0;

        //begin逆序
        TreeMap<Integer, List<Node>> treeMap = new TreeMap<>((o1, o2) -> o2-o1);
        for (int i = 0; i < t.length; i++){
            if (!treeMap.containsKey(t[i])){
                treeMap.put(t[i], new ArrayList<>());
            }
            treeMap.get(t[i]).add(new Node(t[i], w[i]));
            pointAll+= w[i];
        }

        //按照分数的大根堆
        PriorityQueue<Node> heap = new PriorityQueue<>((o1, o2) -> o2.point-o1.point);

        int point = 0;
        for (Integer key: treeMap.keySet()){
            heap.addAll(treeMap.get(key));

            point += heap.poll().point;
        }

        return 2 * point - pointAll; // point - (pointALl - point)
    }

    public static void main(String[] args) {
        int[] t = {1,1,1,2,2,3,3,5,6,6};
        int[] w = {1,1,1,3,1,5,4,1,9,1};

        System.out.println(maxPoint(t, w));
    }
}
