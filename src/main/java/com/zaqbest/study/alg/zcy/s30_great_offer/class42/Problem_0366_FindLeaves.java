package com.zaqbest.study.alg.zcy.s30_great_offer.class42;

//import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一棵二叉树，请按以下要求的顺序收集它的全部节点：
 *
 * 依次从左到右，每次收集并删除所有的叶子节点
 * 重复如上过程直到整棵树为空
 *
 * 输入: [1,2,3,4,5]
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * 输出: [[4,5,3],[2],[1]]
 * 解释:
 * 1. 删除叶子节点 [4,5,3] ，得到如下树结构：
 *
 *           1
 *          /
 *         2
 * 2. 现在删去叶子节点 [2] ，得到如下树结构：
 *
 *           1
 * 3. 现在删去叶子节点 [1] ，得到空树：
 *
 *           []
 *
 * https://cloud.tencent.com/developer/article/1659731
 *
 * 思路：
 *  - dfs
 */

class TreeNode{
    TreeNode left;
    TreeNode right;
    Integer value;

    public TreeNode(Integer value) {
        this.value = value;
    }
}
public class Problem_0366_FindLeaves {

    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, ans);

        return ans;
    }

    private static Integer dfs(TreeNode root, List<List<Integer>> ans){
        if (root == null){
            return -1;
        }

        Integer hl = dfs(root.left, ans);
        Integer hr = dfs(root.right, ans);
        Integer hc = Math.max(hl, hr) + 1;
        if (ans.size() <= hc){
            ans.add(new ArrayList<>());
        }
        ans.get(hc).add(root.value);

        return hc;
    }
    
    //for test
    public static TreeNode buildTree(Integer[] array){
        if(array.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        boolean isLeft = true;
        for(int i = 1; i < array.length; i++){
            TreeNode node = queue.peek();
            if(isLeft){
                if(array[i] != null){
                    node.left = new TreeNode(array[i]);
                    queue.offer(node.left);
                }
                isLeft = false;
            }else {
                if(array[i] != null){
                    node.right = new TreeNode(array[i]);
                    queue.offer(node.right);
                }
                queue.poll();
                isLeft = true;
            }
        }
        return root;
    }
    public static void main(String[] args) {
        TreeNode root = buildTree(new Integer[]{1,2,3,4,5});
        List<List<Integer>> ans = findLeaves(root);
//        System.out.println(JSONUtil.toJsonStr(ans));
    }
}
