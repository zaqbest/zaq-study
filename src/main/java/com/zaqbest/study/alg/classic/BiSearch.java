package com.zaqbest.study.alg.classic;

public class BiSearch {
   /**
     * @param array 已经排序的数组，升序
     * @param target 目标值
     *
     * @return 数组中的索引，未查找到返回-1
     */
    public static int biSearch(int array[], int target){
        if (array == null || array.length == 0)
            return -1;

        int low = 0, high = array.length-1;

        while (low <= high){ // <=
            int mid = (low + high) / 2;

            if (array[mid] == target){
                return mid;
            }
            else if (array[mid] < target){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}

