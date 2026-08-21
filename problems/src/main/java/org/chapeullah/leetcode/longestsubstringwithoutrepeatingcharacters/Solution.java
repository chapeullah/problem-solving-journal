package org.chapeullah.leetcode.longestsubstringwithoutrepeatingcharacters;

import java.util.*;

/*
https://leetcode.com/problems/longest-substring-without-repeating-characters
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int left = 0;
        Set<Character> set = new HashSet<>();
        for (int right = 0; right < s.length(); ++right) {
            if (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left++));
            } else {
                set.add(s.charAt(right));
                result = Math.max(result, set.size());
            }
        }
        return result;
    }
}