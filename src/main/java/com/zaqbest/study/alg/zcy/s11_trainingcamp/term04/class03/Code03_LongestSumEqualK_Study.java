package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class03;

import java.util.HashMap;

public class Code03_LongestSumEqualK_Study {
    public static class Node {
        int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static int ans;

    public static int longest(Node head, int K) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1);
        ans = 0;

        process(head, K, 0,0, sumMap);

        return ans;

    }

    private static void process(Node x, int K, int level, int preSum, HashMap<Integer, Integer> sumMap){
        if (x == null){
            return;
        }

        int allSum = preSum + x.value;
        if (!sumMap.containsKey(allSum)){
            sumMap.put(allSum, level);
        }

        //收集答案
        if (sumMap.containsKey(allSum - K)){
            ans = Math.max(ans, level - sumMap.get(allSum-K));
        }

        process(x.left, K, level+1, allSum, sumMap);
        process(x.right, K, level+1, allSum, sumMap);

        if (sumMap.get(allSum) == level){
            sumMap.remove(allSum);
        }
    }
}
