package com.zaqbest.study.alg.v2ex;

import java.util.PriorityQueue;

/**
 * 题目描述
 * 光明幼儿园的老师非常注意孩子的卫生，他们要给宝宝们常洗晒衣服。幼儿园的李阿姨担负这个重要任务。
 * 她洗完衣服后，就要将衣服弄干。衣服在自然条件下用 1 的时间可以晒干到 A 点湿度。但有时天气很不好，无法晾干，幼儿园买了 1 台烘干机。
 * 为了节省能源，使用烘干机可以让你用 1 的时间使 1 件衣服除开自然晒干的 A 点湿度外，还可以烘干 B 点湿度，但在 1 个时间内只能给 1 件衣服使用。
 *
 * N 件的衣服因为种种原因而需要不一样湿，现在给出每件衣服的湿度，要你求出弄干所有衣服的最少时间（湿度为 0 为干）。
 *
 * 输入格式 第一行 N ，A ，B ；接下来 N 行，每行一个数，表示衣服的湿度（ 1<=湿度，A,B<=500000,1<=N<=500000 ）。
 *
 * 输出格式 一行，最少时间。
 */
public class Problem_DryClothes {

    /**
     * 一次把一件衣服全部烘干的方案
     *
     * @param arr
     * @param A
     * @param B
     * @return
     */
    public static int dry2(int[] arr, int A, int B) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1);
        for (int i = 0; i < arr.length; i++){
            queue.add(arr[i]);
        }

        int elapsed = 0;

        while (true) {
            //队列为空了，或者里面对的所有衣服都干了
            if (queue.isEmpty() || queue.peek() <= elapsed * A) {
                break;
            }

            Integer head1 = queue.poll();

            int t = 0;//使用多少个时间，使得head1的湿度小于head2的湿度
            while (true) {
                //head1已经干了
                if (head1 - t * (A + B) <= elapsed * A) {
                    break;
                }
                t++;
            }
            elapsed += t;
        }

        return elapsed;

    }

    /**
     * 把一件衣服弄干到一定程度后，换下一件衣服烘干
     *
     * @param arr
     * @param A 自然风干的速度
     * @param B 烘干机弄干的速度
     * @return
     */
    public static int dry3(int[] arr, int A, int B){
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1);
        for (int i = 0; i < arr.length; i++){
            queue.add(arr[i]);
        }

        int elapsed = 0;
        while (true){
            //队列为空了，或者里面对的所有衣服都干了
            if (queue.isEmpty() || queue.peek() <= elapsed * A){
                break;
            }

            //多于一件衣服
            if (queue.size() > 1) {
                Integer head1 = queue.poll();
                Integer head2 = queue.peek();

                int t = 0;//使用多少个时间，使得head1的湿度小于head2的湿度
                while (true){

                    int dry1 = head1 - t * (A+B) - elapsed * A;
                    int dry2 = head2 - t * A - elapsed * A;

                    //如果发现head2已经干了，则后面的所有衣服都已经干了
                    if (dry1 <= 0 || dry2 <= 0){
                        if (dry2 <= 0) {
                            //head1还需要多少也能变干
                            int delta = (int) Math.ceil((1.0 * dry1) / (A + B));
                            return elapsed + t + delta;
                        } else {
                            break;
                        }
                    } else{
                        //第一件衣服已经小于第二件衣服湿度
                        if (dry1 < dry2){
                            break;
                        }
                    }
                    t++;
                }

                //总共逝去了多少时间
                //System.out.println(t);
                elapsed += t;
                queue.add(head1 - B * t);
            } else {
                Integer head1 = queue.poll();
                int dry1 = head1 - elapsed * A;
                //head1还需要多少也能变干
                int delta =  (int)Math.ceil((1.0 * dry1) / (A+B));
                return elapsed + delta;
            }
        }

        return elapsed;
    }

    public static void main(String[] args) {
//        for (int i = 0; i< 1000; i++){
//            int[] arr = ArrayUtil.generateRandomArray(100, 1000);
//            int A = new Random().nextInt(10)+1;
//            int B = new Random().nextInt(10)+1;
//
//            int r2 = dry2(arr, A, B);
//            int r3 = dry3(arr, A, B);
//
//            if (r2 != r3){
//                System.out.println(r2);
//                System.out.println(r3);
//                System.out.println("==================");
//                System.out.println(JSONUtil.toJsonStr(arr));
//                System.out.println(A);
//                System.out.println(B);
//
//                break;
//            }
//        }

        int[] arr = {17, 13};
        int A = 6, B = 9;

        int r2 = dry2(arr, A, B);
        int r3 = dry3(arr, A, B);

        System.out.println(r2);
        System.out.println(r3);


    }
}
