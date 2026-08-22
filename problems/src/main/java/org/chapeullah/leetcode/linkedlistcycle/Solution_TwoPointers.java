package org.chapeullah.leetcode.linkedlistcycle;

/*
https://leetcode.com/problems/linked-list-cycle/
 */

public class Solution_TwoPointers {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast == slow) return true;
            slow = slow.next;
        }
        return false;
    }
}
