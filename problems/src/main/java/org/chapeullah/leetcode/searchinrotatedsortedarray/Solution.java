package org.chapeullah.leetcode.searchinrotatedsortedarray;

/*
https://leetcode.com/problems/search-in-rotated-sorted-array/
 */

class Solution {
    public int search(int[] nums, int target) {
        int k = 1, n = nums.length;
        for (int i = 1; i < n; ++i)
            if (nums[i] < nums[i - 1]) {
                k = i;
                break;
            }
        int[] numsSorted = new int[n];
        for (int i = 0; i < n; ++i) {
            numsSorted[i] = nums[(i + k) % n];
        }
        int left = 0, right = n - 1, result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (numsSorted[mid] == target) {
                result = mid;
                break;
            }
            else if (target < numsSorted[mid])
                right = mid - 1;
            else left = mid + 1;
        }
        return result == -1 ? -1 : (result + k) % n;
    }
}
