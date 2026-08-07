package com.zaqbest.study.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取数组的全排列
 * https://leetcode-cn.com/problems/permutations/
 *
 * 关键词：递归，交换
 */
public class Problem_0046_Permute {

    public static void main(String[] args) {
        List<List<Integer>> res = new Problem_0046_Permute().permute(new int[]{1,2,3,4,5});
        for (List l: res){
            System.out.println(l);;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Permutation(nums, 0, nums.length-1, res);
        return res;
    }

    public void Permutation(int[] s, int from, int to, List<List<Integer>> res) {
        if(from == to){
            List<Integer> temp = new ArrayList<>();
            for (int i: s){
                temp.add(i);
            }
            res.add(temp);
        }
        else{
            for(int i=from;i<=to;i++){
                swap(s,i,from);
                Permutation(s,from+1,to, res);
                swap(s,from,i);
            }
        }
    }

    public static void swap(int[] s, int i, int j) {
        int temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
