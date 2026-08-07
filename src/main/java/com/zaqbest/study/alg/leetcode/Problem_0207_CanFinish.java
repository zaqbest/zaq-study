package com.zaqbest.study.alg.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 207: 课程表
 *
 * 算法描述：https://leetcode-cn.com/problems/course-schedule/solution/bao-mu-shi-ti-jie-shou-ba-shou-da-tong-tuo-bu-pai-/
 * 关键词：有向无环图DAG
 */
public class Problem_0207_CanFinish {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //1,课程号与对应的入度
        Map<Integer, Integer> inDegree = new HashMap<>();

        //将所有的课程都放入
        for(int i = 0; i < numCourses; i++){
            inDegree.put(i, 0);
        }

        //依赖关系，保持依赖关系
        Map<Integer, List<Integer>> adj = new HashMap<>();

        //初始化入读和依赖关系
        for(int[] relate: prerequisites){
            // (3,0), 想学3号课程要先完成0号课程, 更新3号课程的入度和0号课程的依赖(邻接表)
            int cur = relate[1];
            int next = relate[0];
            // 1.更新入度
            inDegree.put(next, inDegree.get(next) + 1);
            // 2,当前节点的邻接表
            if (!adj.containsKey(cur)){
                adj.put(cur, new ArrayList<>());
            }
            adj.get(cur).add(next);
        }

        //将入度为0的节点放入队列
        Queue<Integer> queue = new LinkedList<>();
        for(int key: inDegree.keySet()){
            if (inDegree.get(key) == 0){
                queue.offer(key);
            }
        }

        // 取出一个节点，学习对应的课程
        // 遍历当前邻接表，更新其入度；更新之后查看入度，如果是0，加入到队列
        while (!queue.isEmpty()){
            int curr = queue.poll();

            //遍历当前课程的邻接表，更新后续节点的入度
            if (!adj.containsKey(curr)){
                continue;
            }

            List<Integer> successorList = adj.get(curr);
            for(int k: successorList){
                inDegree.put(k, inDegree.get(k) - 1);
                if (inDegree.get(k) == 0){
                    queue.offer(k);
                }
            }
        }

        //4, 遍历入度，如果还有课程入度不为0，返回false
        for (int key: inDegree.keySet()){
            if (inDegree.get(key) != 0){
                return false;
            }
        }

        return true;
    }
}
