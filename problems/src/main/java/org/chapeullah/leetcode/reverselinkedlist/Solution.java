package org.chapeullah.leetcode.reverselinkedlist;

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode result = null;
        while (head != null) {
            result = new ListNode(head.val, result);
            head = head.next;
        }
        return result;
    }
}
