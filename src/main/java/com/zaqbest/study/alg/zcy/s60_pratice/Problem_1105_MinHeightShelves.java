package com.zaqbest.study.alg.zcy.s60_pratice;

/**
 *
 */
public class Problem_1105_MinHeightShelves {
    /**
     * 动态规划
     * @param books
     * @param shelfWidth
     * @return
     */
    public static int minHeightShelves1(int[][] books, int shelfWidth) {
        int N = books.length;
        //dp[i]值得是0~i本书形成的最小高度
        int[] dp = new int[N];

        dp[0] = books[0][1];
        for (int i = 1; i < N; i++){

            int h = books[i][1]; //最后一层书架的最大高度
            int w = books[i][0]; //最后一层书架的占用宽度
            //第i本书作为最后一层的第1本书
            dp[i]  = dp[i-1] + h;
            int j = i-1;//从前面拿下来j位置的书
            while (j >= 0 && w + books[j][0] <= shelfWidth ){
                //刷新宽度
                w += books[j][0];
                //刷新高度
                h = Math.max(h, books[j][1]);
                dp[i] = Math.min(dp[i], (j > 0 ? dp[j-1] : 0) + h);
                j--;
            }
        }

        return dp[N-1];
    }

    public static void main(String[] args) {
//        int[][] books = {{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}};
//        int shelfWidth = 4;
        int[][] books = {{1,3},{2,4},{3,2}};
        int shelfWidth = 6;

        int res1 = minHeightShelves1(books, shelfWidth);
        System.out.println(res1);

        int N = 100000;
        books = new int[N][2];
        for (int i = 0; i < N; i++){
            books[i][0] = 1;
            books[i][1] = 1;
        }
        long start = System.currentTimeMillis();
        int res2 = minHeightShelves1(books, N);
        System.out.println((System.currentTimeMillis()-start) + "ms");
        System.out.println(res2);
    }
}
