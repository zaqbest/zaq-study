package com.zaqbest.study.practice;

import java.util.HashMap;
import java.util.Map;

/**
 *二千亿零一百零一万零二百 转为 200001010200
 * 200001010200
 */
public class StringToInteger {
  public static void main(String[] args) {
    long sum = p("二千亿零一百零一万零二百");
    System.out.println(sum);
  }

  public static long p(String str) {
    if (str == null || str.length() == 0) {
      return 0;
    }

    Map<String, Integer> map = new HashMap<>();
    map.put("零", 0);
    map.put("一", 1);
    map.put("二", 2);
    map.put("三", 3);
    map.put("四", 4);
    map.put("五", 5);
    map.put("六", 6);
    map.put("七", 7);
    map.put("八", 8);
    map.put("九", 9);
    map.put("十", 10);
    map.put("百", 100);
    map.put("千", 1000);
    map.put("万", 10000);
    map.put("亿", 100000000);

    char [] chars = str.toCharArray();
    long ans = 0;
    long anst = 0;
    long num = 0;

    for  (int i = 0; i < chars.length; i++) {
      if (chars[i] == '亿' || chars[i] == '万') {
        ans = ans + (anst + num) * map.getOrDefault(String.valueOf(chars[i]), 0);
        anst =0;
        num = 0;
      } else if (chars[i] == '千' || chars[i] == '百' || chars[i] == '十') {
        anst = anst + num * map.getOrDefault(String.valueOf(chars[i]), 0);
        num = 0;
      } else {
        num = map.get(String.valueOf(chars[i]));
      }
    }

    return ans + anst + num;
  }
}
