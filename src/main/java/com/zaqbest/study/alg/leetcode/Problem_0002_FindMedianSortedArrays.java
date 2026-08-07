package com.zaqbest.study.alg.leetcode;

import com.zaqbest.study.alg.zcy.s11_trainingcamp.term04.class04.Code02_FindKthMinNumber;

/**
 * 4. 寻找两个正序数组的中位数
 *
 * {@link Code02_FindKthMinNumber}
 */
public class Problem_0002_FindMedianSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;

        if (nums1.length ==0 && nums2.length ==0){
            return 0;
        }

        if (nums1.length ==0 ){
            if (nums2.length % 2 ==0){
                return (nums2[nums2.length/2]+nums2[nums2.length/2-1])/2.0;
            } else {
                return nums2[nums2.length/2];
            }
        }

        if (nums2.length ==0 ){
            if (nums1.length % 2 ==0){
                return (nums1[nums1.length/2]+nums1[nums1.length/2-1])/2.0;
            } else {
                return nums1[nums1.length/2];
            }
        }

        if ((N1+N2)%2 == 1){
            return findKthNum(nums1, nums2, (N1+N2)/2+1);
        } else {
            int res1 = findKthNum(nums1, nums2, (N1+N2)/2);
            int res2 = findKthNum(nums1, nums2, (N1+N2)/2+1);

            return (res1+res2)/2.0;
        }
    }


    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        if (arr1 == null || arr2 == null) {
            return -1;
        }
        if (kth < 1 || kth > arr1.length + arr2.length) {
            return -1;
        }
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        if (kth <= s) {
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        if (kth > l) {
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }
        // 短数组长度 < k <= 长数组长度
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
    }

    // A[s1...e1]
    // B[s2...e2]
    // 这两段一定等长且都有序
    // 求这两段整体的上中位数，上中位数值返回
    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            if (A[mid1] == B[mid2]) {
                return A[mid1];
            }
            if (((e1 - s1 + 1) & 1) == 1) { // 奇数长度
                if (A[mid1] > B[mid2]) {
                    if (B[mid2] >= A[mid1 - 1]) {
                        return B[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                } else { // A[mid1] < B[mid2]
                    if (A[mid1] >= B[mid2 - 1]) {
                        return A[mid1];
                    }
                    e2 = mid2 - 1;
                    s1 = mid1 + 1;
                }
            } else { // 偶数长度
                if (A[mid1] > B[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    e2 = mid2;
                    s1 = mid1 + 1;
                }
            }
        }
        return Math.min(A[s1], B[s2]);
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2};

        double res = findMedianSortedArrays(nums1, nums2);
        System.out.println(res);
    }
}
