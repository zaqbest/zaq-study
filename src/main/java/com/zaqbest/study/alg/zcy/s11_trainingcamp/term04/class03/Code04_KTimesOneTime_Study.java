package com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class03;

public class Code04_KTimesOneTime_Study {
    public static int onceNum(int[] arr, int K) {
        int[] e0 = new int[32];

        for (int i = 0; i < arr.length; i++){
            setExclusiveOr(e0, arr[i], K);
        }

        int res = getNumFromKSysNum(e0, K);

        return res;
    }

    private static int getNumFromKSysNum(int[] e0, int k) {
        int res = 0;
        for (int i = e0.length-1; i >= 0; i--){
            res = res*k + e0[i];
        }

        return res;
    }

    private static void setExclusiveOr(int[] e0, int num, int K) {
        int[] curKSysNum = getKSysNumFromNum(num, K);
        for (int i =0; i < e0.length; i++){
            e0[i] = (e0[i] + curKSysNum[i])%K;
        }
    }

    private static int[] getKSysNumFromNum(int num, int k) {
        int[] res = new int[32];
        int index = 0;
        while (num != 0){
            res[index++] = num % k;
            num = num / k;
        }

        return res;
    }

    public static void main(String[] args) {
        int[] test1 = { 1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9 };
        System.out.println(onceNum(test1, 3));

        int[] test2 = { -1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2 };
        System.out.println(onceNum(test2, 5));
    }


}
