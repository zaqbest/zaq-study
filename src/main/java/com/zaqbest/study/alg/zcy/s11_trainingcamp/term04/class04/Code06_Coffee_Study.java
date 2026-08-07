package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class04;

//import cn.hutool.json.JSONUtil;

import java.util.PriorityQueue;

public class Code06_Coffee_Study {
    static class CoffeeMachine{
        public int start;
        public int work;

        public CoffeeMachine(int start, int work){
            this.start = start;
            this.work = work;
        }
    }

    public static int[] bestChoices(int[] arr, int M) {
        int[] ans = new int[M];
        PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>((o1, o2) -> o1.start+o1.work-o2.start-o2.work);

        for (int n: arr){
            heap.add(new CoffeeMachine(0, n));
        }

        for (int i = 0; i < M; i++){
            CoffeeMachine cur = heap.poll();
            ans[i] = cur.start + cur.work;

            heap.add(new CoffeeMachine(cur.start+ cur.work, cur.work));
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] ans = bestChoices(new int[]{1,3,5}, 10);
//        System.out.println(JSONUtil.toJsonStr(ans));
//        System.out.println(JSONUtil.toJsonStr(Code06_Coffee.bestChoices(new int[]{1,3,5}, 10)));
    }

}
