package com.zaqbest.study.alg.zcy.s10_primary.class10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 合并用户
 */
public class Code07_MergeUsers {
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }
    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes;//V值对应的Node
        public HashMap<Node<V>, Node<V>> parents;//key-当前节点，value-代表节点
        public HashMap<Node<V>, Integer> sizeMap; //集合的大小

        public UnionSet(List<V> values) {
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // cur头节点
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if (aSetSize >= bSetSize) {
                    parents.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                    sizeMap.remove(bHead);
                } else {
                    parents.put(aHead, bHead);
                    sizeMap.put(bHead, aSetSize + bSetSize);
                    sizeMap.remove(aHead);
                }
            }
        }

        public int getSetNum() {
            return sizeMap.size();
        }
    }

    public static class User {
        public String a;
        public String b;
        public String c;
    }

    /**
     * 如何两个user, a字段一样，或者b字段一样，或者c字段一样，则认为是同一个用户
     *
     * @param users
     * @return
     */
    public static int mergetUsers(List<User> users) {

        UnionSet<User> unionSet = new UnionSet<>(users);
        Map<String, User> mapA = new HashMap<>();
        Map<String, User> mapB = new HashMap<>();
        Map<String, User> mapC = new HashMap<>();

        for (User user : users){
            if (mapA.containsKey(user.a)){
                unionSet.union(user, mapA.get(user.a));
            } else {
                mapA.put(user.a, user);
            }

            if (mapB.containsKey(user.b)){
                unionSet.union(user, mapB.get(user.b));
            } else {
                mapB.put(user.b, user);
            }

            if (mapC.containsKey(user.c)){
                unionSet.union(user, mapC.get(user.c));
            } else {
                mapC.put(user.c, user);
            }
        }

        //向并查集询问合并之后还剩多少个集合
        return unionSet.getSetNum();
    }
}
