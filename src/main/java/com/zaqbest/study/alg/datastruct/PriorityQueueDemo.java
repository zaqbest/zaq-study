package com.zaqbest.study.alg.datastruct;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        Queue<String> priQueue = new PriorityQueue<>(20, (o1, o2) -> {
            //return o1.compareTo(o2);
            if (o1.charAt(0) == 'V' || o2.charAt(0) == 'V'){
                //两个V的情况
                if (o1.charAt(0) == o2.charAt(0)){
                    return o1.compareTo(o2);
                }
                else {
                    //一个Vxx，另外一个不是Vxx
                    return o2.compareTo(o1);
                }
            }
            return o1.substring(1).compareTo(o2.substring(1));
        });

        priQueue.add("A01");
        priQueue.add("A02");
        priQueue.add("V01");
        priQueue.add("V02");
        priQueue.add("A05");
        priQueue.add("B06");
        priQueue.add("B07");
        priQueue.add("A08");
        priQueue.add("B09");
        priQueue.add("V03");

        while (!priQueue.isEmpty()){
            System.out.println(priQueue.remove());
        }
    }
}
