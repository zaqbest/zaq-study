package com.zaqbest.study.alg.antfin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 统计多个文件中单词出现的次数
 *
 * 要求速度最快
 * 1,文件读取采用BufferReader
 * 2，线程池采用固定线程池，线程数量不宜过多（线程切换成本）
 * 2,采用多线程分别统计，主线程合并结构
 * 2.1 线程同步采用CountDownLatch实现
 * 2.2 工作线程完成后再Main线程合并结果
 *
 */
public class WordStastisticsSolution {

    public static void main(String[] args) throws InterruptedException, IOException {
        WordStastisticsSolution solution = new WordStastisticsSolution();
        List<String> files = Arrays.asList(
                "D:\\IdeaProjects\\my_projects\\my_dev\\walle\\walle-study\\src\\main\\resources\\data\\001.txt",
                "D:\\IdeaProjects\\my_projects\\my_dev\\walle\\walle-study\\src\\main\\resources\\data\\002.txt"
        );
        Map<String, Integer> result = solution.wordStat(files);
        List<Map.Entry<String, Integer>> sort = result.entrySet().stream()
                .sorted((a, b)->b.getValue()-a.getValue())
                .collect(Collectors.toList());
        System.out.println(sort);
    }

    Map<String, Integer> wordStat(List<String> files) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        Map<String, Integer> finalStat = new ConcurrentHashMap<>(); //最终结果
        CountDownLatch countDownLatch = new CountDownLatch(files.size());

        for(String f: files){
            threadPool.submit(new WorkThread(f, finalStat, countDownLatch));
        }
        countDownLatch.await();
        threadPool.shutdownNow();

        return finalStat;
    }


    /**
     * 文件不存在，或者其他异常 也算处理完成
     */
    class WorkThread implements Runnable {

        private String file;
        private Map<String, Integer> finalStat;
        private CountDownLatch countDownLatch;

        public WorkThread(String file, Map<String, Integer> finalStat, CountDownLatch countDownLatch) {
            this.file = file;
            this.finalStat = finalStat;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));

                String line;
                List<String> wordList = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    if (line.isEmpty()) continue;
                    String[] words = line.trim().split(" ");
                    wordList.addAll(Arrays.asList(words));
                }

                //合并结果
                wordList.stream()
                        .collect(Collectors.toMap(e->e, e->1, Integer::sum))
                        .forEach((k,v)->finalStat.merge(k, v, Integer::sum));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}
