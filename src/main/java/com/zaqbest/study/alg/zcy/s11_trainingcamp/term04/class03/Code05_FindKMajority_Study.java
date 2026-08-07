package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class03;

import java.util.HashMap;

public class Code05_FindKMajority_Study {
    public static void printHalfMajor(int[] arr) {
        int cand = 0;
        int hp = 0;

        for (int n: arr){
            if (hp == 0){
                cand = n;
                hp++;
            } else if (cand == n) {
                hp++;
            } else {
                hp--;
            }
        }

        if (hp > 0) {
            int count = 0;
            for (int n: arr){
                if (cand == n) count++;
            }

            if (count > arr.length / 2){
                System.out.println("shuiwang="+cand);
            } else {
                System.out.println("not found");
            }
        } else {
            System.out.println("not found");
        }
    }

    public static void printKMajor(int[] arr, int K){
        if (K < 2){
            System.out.println("k must greater than 1");
            return;
        }

        HashMap<Integer, Integer> candMap = new HashMap<>();
        for (int n: arr){
            if (candMap.containsKey(n)){
                candMap.put(n ,candMap.get(n) + 1);
            } else {
                if (candMap.size() == K-1){
                    minusOneForAll(candMap);
                } else {
                    candMap.put(n, 1);
                }
            }
        }

        HashMap<Integer, Integer> realCands = getRealCands(arr, candMap);

        for (Integer key: realCands.keySet()){
            if (realCands.get(key) > arr.length / K){
                System.out.println("key="+key);
            }
        }

    }

    private static HashMap<Integer, Integer> getRealCands(int[] arr, HashMap<Integer, Integer> candMap) {
        HashMap<Integer, Integer> realCands = new HashMap<>();
        for (Integer key: candMap.keySet()){
            realCands.put(key, 0);
        }

        for (int n: arr){
            if (realCands.containsKey(n)){
                realCands.put(n, realCands.get(n) + 1);
            }
        }

        return realCands;
    }

    private static void minusOneForAll(HashMap<Integer, Integer> candMap) {
        for (Integer key: candMap.keySet()){
            if (candMap.get(key) == 1){
                candMap.remove(key);
            } else {
                candMap.put(key, candMap.get(key-1));
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 1, 2, 1 };
        printHalfMajor(arr);
        int K = 4;
        printKMajor(arr, K);
    }

}
