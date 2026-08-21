package org.chapeullah.leetcode.linkedlistcycle;

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();

        // [3,2,0,-4], pos = 1; Output: true
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        head1.next.next.next.next = head1.next;

        // [1,2], pos = 0; Output: true
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = head2;

        // [1], pos = -1; Output: false
        ListNode head3 = new ListNode(1);

        System.out.println(s.hasCycle(head1)); // true
        System.out.println(s.hasCycle(head2)); // true
        System.out.println(s.hasCycle(head3)); // false
    }
}