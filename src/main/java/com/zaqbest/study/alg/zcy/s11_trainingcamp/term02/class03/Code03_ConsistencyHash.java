package com.zaqbest.study.alg.zcy.s11_trainingcamp.term02.class03;

//import cn.hutool.crypto.digest.DigestUtil;
//import cn.hutool.json.JSONUtil;

import cn.hutool.crypto.digest.DigestUtil;

import java.util.Map;
import java.util.TreeMap;

/**
 * 一致性hash简单实现
 */
public class Code03_ConsistencyHash {
    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        int nMachine = 5;

        //初始化hash轮盘
        for (int i = 0; i < nMachine * 1000; i++){
            treeMap.put(getHashCode(String.valueOf(i)), (i %nMachine));
        }

        //测试数据分布
        int[] count = new int[nMachine];
        for (int i = 0; i < 10000000; i++){
            String tmpHashCode = getHashCode(String.valueOf(i));
            Map.Entry<String, Integer> res;
            //获取下一个节点（按照key排序后，比当前节点大或者相等的key)
            res = treeMap.ceilingEntry(tmpHashCode);
            if (res == null) {
                res = treeMap.firstEntry();
            }
            count[res.getValue()]++;
        }

        //输入结果
//        System.out.println(JSONUtil.toJsonStr(count));
    }

    private static String getHashCode(String str){
        return DigestUtil.md5Hex(str);
    }
}
