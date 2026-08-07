package com.zaqbest.study.alg.utils;

//import cn.hutool.core.util.StrUtil;

import java.util.Arrays;

public class ArrayUtil {
    /**
     * 生成随机数组
     * @param maxSize 最大数组长度
     * @param maxValue 最大值
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            arr[i] = Math.abs(arr[i]);
        }
        return arr;
    }

    /**
     * 复制数组
     * @param origArr 待复制数组
     * @return
     */
    public static int[] copyOfArray(int[] origArr) {
        return Arrays.copyOf(origArr, origArr.length);
    }

    public static int[] sort(int[] arr){
        Arrays.sort(arr);

        return arr;
    }

    /**
     * 交换数组两个元素
     *
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 打印数组对象
     *
     * @param array
     */
    public static void printArray(int[] array){
        if (array == null || array.length == 0){
            return;
        }

        System.out.println("======== array 数据 =========");
        for(int e: array){
            System.out.printf("%d ", e);
        }
    }

    /**
     * 打印数组对象
     *
     * @param array
     */
    public static void printMatrix(int[][] array){
        if (array == null || array.length == 0){
            return;
        }


        int maxLen = 0;
        for(int[] a: array) {
            for (int e : a) {
                maxLen = Math.max(maxLen, String.valueOf(e).length());
            }
        }
        for(int[] a: array){
            for(int e: a){
//                System.out.printf(StrUtil.format("%{}d", maxLen+3), e);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        System.out.printf(StrUtil.format("%{}d", 10), 4);
    }
}
