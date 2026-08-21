package org.chapeullah.leetcode.longestsubstringwithoutrepeatingcharacters;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.lengthOfLongestSubstring("abcabcbb")); // Answer: 3
        System.out.println(s.lengthOfLongestSubstring("bbbbb")); // Answer: 1
        System.out.println(s.lengthOfLongestSubstring("pwwkew")); // Answer: 3
    }
}
